package com.holylala.seedskill.dao;

import com.holylala.seedskill.model.Seckill;
import com.holylala.seedskill.model.SuccessKilled;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * @author zhenqionghuang@creditease.cn
 * @version V1.0
 * @Package com.holylala.seedskill.dao
 * @Description:
 * @date 2016/10/29 23:21
 * @Company: creditease.cn
 */
// 配置Spring和junit整合 Junit启动时加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SuccessKilledDaoTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    //@Test
    public void testInsertSuccessKilled() throws Exception {
        int result = successKilledDao.insertSuccessKilled(1L,"18618192456");
        System.out.println("insert success:>>>"+result);
    }

    //@Test
    public void testQueryByIdWithSeckill() throws Exception {

        SuccessKilled successKilled  = successKilledDao.queryByIdWithSeckill(1L,"18618192456");
        System.out.println("successKilled:>>"+successKilled);
        Seckill seckill = successKilled.getSeckill();
        System.out.println("seckill:>>"+seckill);
    }
}