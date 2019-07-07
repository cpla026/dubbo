package com.coolron.task;

import com.coolron.common.utils.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Auther: xf
 * @Date: 2018/11/27 21:20
 * @Description:
 */
@Slf4j
@RequestMapping(value = "async")
@RestController
public class TaskController {

    @Autowired
    private AsyncTask asyncTask;

    @GetMapping(value = "job01")
    public ApiResult job01() throws ExecutionException, InterruptedException {
        long currentTime = System.currentTimeMillis();

        log.info(currentTime + ": >>>>>>>>>>开始执行任务：job01() >>>>>>>>>>>>");
        Future<String> future = asyncTask.task01(currentTime);

        //String s = future.get();  // 获取执行结果
        boolean done = future.isDone();  // 任务是否完成
        boolean cancelled = future.isCancelled();  // 任务是否取消
        //TimeUnit.SECONDS.sleep(2);
        //boolean cancel = future.cancel(true);
        //boolean isCancelled = future.isCancelled();
        log.info(currentTime + ": >>>>>>>>>>结束执行任务：job01() >>>>>>>>>>>>" + done + " " + cancelled );
        return ApiResult.ok();
    }
}
