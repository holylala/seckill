package com.holylala.seedskill.exception;

/**
 * @author zhenqionghuang@creditease.cn
 * @version V1.0
 * @Package com.holylala.seedskill.exception
 * @Description: 重复秒杀异常(运行期异常)
 * @date 2016/10/30 22:11
 * @Company: creditease.cn
 */
public class RepeatKillException extends SeckillException {


    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}

// 异常 编译期异常 运行期异常(不需要手动try catch)
//spring 声明式事务只接收运行期异常的回滚 非运行期异常不会回滚