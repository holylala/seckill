package com.holylala.seedskill.service.impl;

import com.holylala.seedskill.dao.SeckillDao;
import com.holylala.seedskill.dao.SuccessKilledDao;
import com.holylala.seedskill.dto.Exposer;
import com.holylala.seedskill.dto.SeckillExecution;
import com.holylala.seedskill.enums.SeckillStatEnum;
import com.holylala.seedskill.exception.RepeatKillException;
import com.holylala.seedskill.exception.SeckillCloseException;
import com.holylala.seedskill.exception.SeckillException;
import com.holylala.seedskill.model.Seckill;
import com.holylala.seedskill.model.SuccessKilled;
import com.holylala.seedskill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @author huangzhenqiong@sina.cn
 * @version V1.0
 * @Package com.holylala.seedskill.service.impl
 * @Description:
 * @date 2016/10/31 21:57
 * @Company:
 */
// @Component 综合的一个组件  @Service @Dao @Controller
@Service
public class SeckillServiceImpl implements SeckillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //注入Service依赖
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    private final String slat = "dfsdfewfsdf*&323aad3883:{>";//混淆字符

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0,10);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {

        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false,seckillId);
        }

        Date startTime = seckill.getStartTime();
        Date endTime = seckill.getEndTime();
        Date nowTime = new Date();

        //秒杀未开始 或者已经结束
        if (nowTime.getTime() < startTime.getTime() || nowTime.getTime() > endTime.getTime()) {
            return new Exposer(false,seckillId,nowTime.getTime(),startTime.getTime(),endTime.getTime());
        }
        String md5 = getMD5(seckillId); //转化特定字符串的过程,不可逆
        return new Exposer(seckillId,md5,true);
    }

    private String getMD5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return  md5;
    }

    //执行秒杀的操作
    // 保证事务方法的执行时间尽可能短,不要穿插其他网络操作 如 RPC/HTTP 请求 (剥离到方法外）
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, String userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {

        if (md5 == null || !md5.equals(getMD5(seckillId))) {
            throw new SeckillException("seckill data rewrite");
        }

        //减库存
        Date nowTime = new Date();
        try {
            int updateCount = seckillDao.reduceNumber(seckillId, nowTime);
            if (updateCount <= 0) {
                //没有更新成功
                throw new SeckillCloseException("seckill is close");
            } else {
                //记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) { //重复秒杀
                    throw new RepeatKillException("seckill repeat");
                } else {//秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }  catch (SeckillCloseException e1) {
            throw e1;
        }  catch (RepeatKillException e2) {
            throw e2;
        }  catch (Exception e) {
            logger.error(e.getMessage(),e);
            //抛出运行期异常 spring会做回滚
            throw new SeckillException("seckill inner error:"+e.getMessage());
        }
    }

}
