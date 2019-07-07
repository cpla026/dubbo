package com.coolron.job;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/25 14:01
 * @Description:  定时任务
 */
@Slf4j
//@Component
public class MyTask {

    /**
     * 指定具体的时间点去执行
     * 效果：具体时间点的 10秒 20秒 30秒... 执行
     */
    //@Scheduled(cron = "0/10 * * * * ?")
    private void cornTest() {
        log.info(">>>>>corn 执行时间：{}", new Date());
    }

    /**
     * 上一次调用开始后再次调用的间隔
     * 下效果：第一次调用开始 5 秒调用第二次 不管前一次是否调用完成
     */
    //@Scheduled(fixedRate = 1000 * 5)
    private void fixedRateTest() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(">>>>>fixedRate 执行时间：{}", new Date());
    }

    /**
     * 上一次调用完成后再次调用的间隔
     * 效果：第一次调用完 5 秒之后调用第二次
     */
    //@Scheduled(fixedDelay = 1000 * 5)
    private void fixedDelayTest() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(">>>>>fixedDelay 执行时间：{}", new Date());
    }

    /**
     * initialDelay 只是做第一次执行前的延时  配合 fixedDelay 和 fixedRate 使用
     * 效果： 第一次执行之前延时 5 秒
     */
    //@Scheduled(initialDelay = 1000 * 5, fixedDelay = 1000 * 2)
    //@Scheduled(initialDelay = 1000 * 5, fixedDelay = 1000 * 2)
    private void initialDelayTest() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(">>>>>initialDelay 执行时间：{}", new Date());
    }

}
