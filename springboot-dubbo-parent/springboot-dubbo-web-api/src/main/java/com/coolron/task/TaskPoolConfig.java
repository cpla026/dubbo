package com.coolron.task;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Auther: xf
 * @Date: 2018/11/27 22:45
 * @Description: 自定义线程池
 */
//@Configuration
public class TaskPoolConfig {
    @Bean("taskExecutor")
    public Executor taskExecutor() {
        // 创建线程池
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程数，初始化线程数
        executor.setCorePoolSize(10);
        // 最大线程数
        executor.setMaxPoolSize(20);
        // 缓存队列
        executor.setQueueCapacity(200);
        // 空闲时间
        executor.setKeepAliveSeconds(60);
        // 线程前缀
        executor.setThreadNamePrefix("custom-exec-");
        // 拒绝任务的处理器
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
        executor.setWaitForTasksToCompleteOnShutdown(true);
        // 设置线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁，以确保应用最后能够被关闭，而不是阻塞住
        executor.setAwaitTerminationSeconds(60);
        return executor;
    }
}
