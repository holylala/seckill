package com.holylala.seedskill.dao;

import com.holylala.seedskill.model.Seckill;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @author zhenqionghuang@creditease.cn
 * @version V1.0
 * @Package com.holylala.seedskill.dao
 * @Description:
 * @date 2016/10/24 23:42
 * @Company: creditease.cn
 */
//自动生成代码测试----->在待编写测试的java类源码块上按快捷键ctrl+shift+T
public interface SeckillDao {


    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 查商品
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 查询所有商品
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(@Param("offset") int offset, @Param("limit") int limit);
}
