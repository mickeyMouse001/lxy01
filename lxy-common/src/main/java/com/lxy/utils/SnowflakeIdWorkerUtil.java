package com.lxy.utils;

import java.math.BigInteger;

/**
*<p>Title:分布式唯一id生成工具类 </p>
* <p>Description:Snowflake算法 </p>
* <p>Company: hlb</p> 
* @author: lxh
* <p>date：2018/5/7</p>
 */
public class SnowflakeIdWorkerUtil {

	/**时间戳的起始时间*/
    private static final long twepoch = 1420041600000L;

    /** 工作站的bit长度*/
    private static final long workerIdBits = 5L;

    /**数据中心的bit长度*/
    private static final long datacenterIdBits = 4L;//(0~15)

    /**工作站的最大值*/
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**数据中心的最大值*/
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /** 序列号的bit长度 */
    private static final long sequenceBits = 13L;//(2^13-1=8191),范围：0~8191

    /**工作站左位移12位 */
    private static final long workerIdShift = sequenceBits;

    /** 数据中心左位移17位(12+5) */
    private static final long datacenterIdShift = sequenceBits + workerIdBits;

    /** 时间戳左位移22位(5+5+12) */
    private static final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /** 掩码8191 (01,1111,1111,1111=0x1fff=8191)，保证8191范围内不会溢出 */
    private static final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /** 工作站ID(0~31) */
    private static long workerId=1L;//服务器默认为1

    /** 数据中心ID(0~31) */
    private static long datacenterId=1L;//机房默认为1

    /** 序列号13位bit(0~8191) */
    private static long sequence = 0L;

    /** 上一次时间戳*/
    private static long lastTimestamp = -1L; 
    public static void main(String[] args) {
    	System.out.println(SnowflakeIdWorkerUtil.nextId());
	}

    //==============================Constructors=====================================
    /**
     * 构造函数
     * @param workerId 工作站ID (0~31)
     * @param datacenterId 数据中心ID---服务器id (0~31)
     */
    public SnowflakeIdWorkerUtil(long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
    }

    // ==============================Methods==========================================
    /**
     * 获取下一个id(线程安全)
     * @return SnowflakeId
     */
    public static synchronized long nextId() {
        long timestamp = timeGen();

        //当前时间比上次的小
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(
                    String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        //如果毫秒数相同，进行取毫秒内序列号
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            //序列号溢出
            if (sequence == 0) {
                //取下一秒时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        //时间戳不相同时，无需取序列号，置0
        else {
            sequence = 0L;
        }

        //更新上一次时间戳
        lastTimestamp = timestamp;

        //通过二进制位移运算组合成最终的id
        return ((timestamp - twepoch) << timestampLeftShift) //
                | (datacenterId << datacenterIdShift) //
                | (workerId << workerIdShift) //
                | sequence;
    }

    /**
     * 阻塞到下一秒，直到当前时间比上一次的大
     * @param lastTimestamp 上次的时间戳
     * @return 当前时间戳
     */
    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回毫秒级别的当前时间
     * @return 当前时间（毫秒）
     */
    protected static long timeGen() {
        return System.currentTimeMillis();
    }
    
   
}
