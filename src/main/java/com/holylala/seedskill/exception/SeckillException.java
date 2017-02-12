package com.holylala.seedskill.exception;

/**
 * @author huangzhenqiong@sina.cn
 * @version V1.0
 * @Package com.holylala.seedskill.exception
 * @Description: 所有秒杀相关的异常类
 * @date 2016/10/30 22:23
 * @Company:
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
