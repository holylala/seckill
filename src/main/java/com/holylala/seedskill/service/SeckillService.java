package com.holylala.seedskill.service;

import com.holylala.seedskill.dto.Exposer;
import com.holylala.seedskill.dto.SeckillExecution;
import com.holylala.seedskill.exception.RepeatKillException;
import com.holylala.seedskill.exception.SeckillCloseException;
import com.holylala.seedskill.exception.SeckillException;
import com.holylala.seedskill.model.Seckill;

import java.util.List;

/**
 * @author zhenqionghuang@creditease.cn
 * @version V1.0
 * @Package com.holylala.seedskill.service.impl
 * @Description: 业务接口：站在 使用者 角度设计接口
 * 1.方法定义粒度
 * 2.参数简略
 * 3.返回类型友好
 * @date 2016/10/30 21:45
 * @Company: creditease.cn
 */
public interface SeckillService {

    /**
     * 查询所有秒杀商品记录
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 查询某个秒杀记录
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时 输出秒杀接口地址 否则输出系统时间和秒杀时间
       让用户拼不出秒杀的url
     *  @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userPhone
     * @param md5 用于发布的md5 url的验证
     */
    SeckillExecution executeSeckill(long seckillId, String userPhone, String md5) throws SeckillException,RepeatKillException,SeckillCloseException;
}
