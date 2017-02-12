package com.holylala.seedskill.dto;

/**
 * Dto 包 方便Service层数据返回的封装
 * @author zhenqionghuang@creditease.cn
 * @version V1.0
 * @Package com.holylala.seedskill.dto
 * @Description:暴露秒杀地址DTO
 * @date 2016/10/30 21:54
 * @Company: creditease.cn
 */
public class Exposer {

    private boolean exposed;//秒杀是否开启

    private String md5;//一种加密措施

    private long seckillId;

    private long now;//系统当前时间

    private long start; //秒杀开始时间

    private long end;//秒杀结束时间

    public Exposer(long seckillId, String md5, boolean exposed) {
        this.seckillId = seckillId;
        this.md5 = md5;
        this.exposed = exposed;
    }

    public Exposer(boolean exposed,long seckillId, long now, long start, long end) {
        this.exposed = exposed;
        this.seckillId = seckillId;
        this.now = now;
        this.start = start;
        this.end = end;
    }

    public Exposer(boolean exposed, long seckillId) {
        this.exposed = exposed;
        this.seckillId = seckillId;
    }

    public boolean isExposed() {
        return exposed;
    }

    public void setExposed(boolean exposed) {
        this.exposed = exposed;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + seckillId +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
