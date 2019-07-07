package com.coolron.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: xf
 * @Date: 2018/11/27 21:08
 * @Description:
 */
@Slf4j
@Component
public class AsyncTask {

    /*
     *  使用@Async注解标注这是一个异步函数
     */
    //@Async("taskExecutor")
    @Async
    public Future<String> task01(long currentTime){
        log.info(currentTime + ": >>>>>>>>>>进入异步方法：task01() >>>>>>>>>>>>");
        try {
            // 休眠5秒
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(currentTime + ": >>>>>>>>>>离开异步方法：task01()>>>>>>>>>>>>>");
        return new AsyncResult<>("task01回调");
    }
}
