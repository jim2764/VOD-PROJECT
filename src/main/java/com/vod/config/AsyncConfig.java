package com.vod.config; // ⚠️ 請確保這裡的 package 名稱與你的專案目錄結構一致

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    @Bean(name = "videoExecutor")
    public Executor videoExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        
        // 同時處理 2 個轉碼任務（適合一般開發環境，不吃光 CPU）
        executor.setCorePoolSize(2); 
        
        // 最大執行緒數
        executor.setMaxPoolSize(4);
        
        // 隊列容量，超過此數量的任務會排隊
        executor.setQueueCapacity(500);
        
        // 給 Thread 起個好辨認的名字，之後看 Log 才知道是 FFmpeg 在跑
        executor.setThreadNamePrefix("FFmpeg-Worker-");
        
        executor.initialize();

        // 是否要改為java21+的引擎?!
        return executor;
    }
}