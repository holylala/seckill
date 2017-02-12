package com.holylala.seedskill.enums;

/**
 * @author huangzhenqiong@sina.cn
 * @version V1.0
 * @Package com.holylala.seedskill.enums
 * @Description:
 * @date 2016/11/1 22:29
 * @Company:
 */
public enum SeckillStatEnum {

    SUCCESS(1,"秒杀成功"),
    END(0,"秒杀结束"),
    REPEAT_KILL(-1,"重复秒杀"),
    INNER_ERROR(-2,"系统异常"),
    DATA_REWRITE(-3,"数据篡改");

    private int state;
    private String stateinfo;

    SeckillStatEnum(int state, String stateinfo) {
        this.state = state;
        this.stateinfo = stateinfo;
    }

    public int getState() {
        return state;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public static SeckillStatEnum stateOf(int index) {
        for (SeckillStatEnum statEnums : values()) {
            if (statEnums.getState() == index) {
                return statEnums;
            }
        }
        return null;
    }

}
