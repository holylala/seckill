package com.holylala.seedskill.dao;

import com.holylala.seedskill.model.SuccessKilled;

/**
 * @author zhenqionghuang@creditease.cn
 * @version V1.0
 * @Package com.holylala.seedskill.dao
 * @Description:
 * @date 2016/10/24 23:46
 * @Company: creditease.cn
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细 可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insertSuccessKilled(long seckillId,String userPhone);

    /**
     * 根据Id查询成功的秒杀 带有对应的秒杀产品信息
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(long seckillId);

}
