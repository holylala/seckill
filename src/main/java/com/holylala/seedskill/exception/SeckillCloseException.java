package com.holylala.seedskill.exception;

/**
 * @author huangzhenqiong@sina.cn
 * @version V1.0
 * @Package com.holylala.seedskill.exception
 * @Description: 秒杀关闭异常
 * @date 2016/10/30 22:21
 * @Company:
 */
public class SeckillCloseException  extends SeckillException {

    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
