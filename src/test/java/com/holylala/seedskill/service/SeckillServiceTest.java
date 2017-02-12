package com.holylala.seedskill.service;

import com.holylala.seedskill.dto.Exposer;
import com.holylala.seedskill.dto.SeckillExecution;
import com.holylala.seedskill.exception.RepeatKillException;
import com.holylala.seedskill.model.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @author huangzhenqiong@sina.cn
 * @version V1.0
 * @Package com.holylala.seedskill.service
 * @Description:
 * @date 2016/11/12 22:13
 * @Company:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-services.xml"})
public class SeckillServiceTest {

    @Autowired
    private SeckillService seckillService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //@Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}",list);//给定一个占位符
    }

    //@Test
    public void testGetById() throws Exception {
        Seckill seckill = seckillService.getById(1L);
        logger.info("seckill={}",seckill);
    }

    //@Test
    public void testExportSeckillUrl() throws Exception {
        Exposer exposer =  seckillService.exportSeckillUrl(1L);
        logger.info("exposer={}",exposer);
        //exposer=Exposer{exposed=true,
        // md5='4f2a0f2a3676072a46069cf50c05d288', seckillId=1, now=0, start=0, end=0}
    }

    //@Test
    public void testExecuteSeckill() throws Exception {
        long id = 1L;
        try {
            SeckillExecution execution = seckillService.executeSeckill(id, "18618192433", "4f2a0f2a3676072a46069cf50c05d288");
            logger.info("execution={}", execution);
        } catch (RepeatKillException e1) {
            logger.error(e1.getMessage(),e1);
        }
        //execution=SeckillExecution{seckillId=1, state=1, stateInfo='秒杀成功',
        // successKilled=SuccessKilled{seckillId=1, userPhone='18618192433', state=0, createTime=Sun Nov 13 00:10:04 CST 2016}}

        //如果再次执行则：com.holylala.seedskill.exception.RepeatKillException: seckill repeat
    }

    //@Test
    public void testExecuteSeckillLogic() throws Exception {

        Exposer exposer =  seckillService.exportSeckillUrl(2L);

        if (exposer.isExposed()) { //如果秒杀开始了
            logger.info("exposer={}",exposer);

            //执行秒杀流程
            try {
                SeckillExecution execution = seckillService.executeSeckill(exposer.getSeckillId(), "18618192477", exposer.getMd5());
                logger.info("execution={}", execution);
            } catch (RepeatKillException e1) {
                logger.error(e1.getMessage(),e1);
            }
        } else { //秒杀结束
            logger.warn("exposer={}",exposer);
        }

    }
}