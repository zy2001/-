package com.openjudge.backend.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by zy on 2020/2/11
 */

@Configuration
public class TaskExecutePoolConfig {

    @Bean
    public ThreadPoolTaskExecutor tashPool(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(4);
        //配置队列大小
        executor.setQueueCapacity(6);
        //配置最大线程数
        executor.setMaxPoolSize(8);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("taskPool");
        // 拒绝策略：当pool已经达到max size的时候，由调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //初始化
        executor.initialize();
        return executor;
    }
}
