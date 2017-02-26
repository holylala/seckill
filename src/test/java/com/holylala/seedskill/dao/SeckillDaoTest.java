package com.holylala.seedskill.dao;

import com.holylala.seedskill.model.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhenqionghuang@creditease.cn
 * @version V1.0
 * @Package com.holylala.seedskill.dao
 * @Description:
 * @date 2016/10/29 22:46
 * @Company: creditease.cn
 */
// 配置Spring和junit整合 Junit启动时加载springIOC容器
@RunWith(SpringJUnit4ClassRunner.class)
//告诉Junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

//    // 注入Dao实现类依赖
//
//    @Resource
//    private SeckillDao seckillDao;
//
//
//
//    //@Test
//    public void testQueryById() throws Exception {
//        Seckill seckill = seckillDao.queryById(1L);
//        System.out.println(seckill);
//    }
//
//    //@Test
//    public void testQueryAll() throws Exception {
//        List<Seckill> seckillList = seckillDao.queryAll(0,10);
//        for (Seckill seckill : seckillList) {
//            System.out.println(seckill);
//        }
//    }
//
//    //@Test
//    public void testReduceNumber() throws Exception {
//        Date date = new Date();
//        int result = seckillDao.reduceNumber(1L,date);
//        System.out.println(result);
//    }

}