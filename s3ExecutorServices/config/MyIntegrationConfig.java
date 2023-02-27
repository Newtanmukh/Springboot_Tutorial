package com.amazons3.amazon.config;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.ExecutorChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import static com.amazons3.amazon.config.AppConstants.corePoolSize;
import static com.amazons3.amazon.config.AppConstants.MaxPoolSize;
import static com.amazons3.amazon.config.AppConstants.QueueCapacity;
@Configuration
@EnableIntegration
@Component

public class MyIntegrationConfig {

//    @Bean(name = "FileReaderChannel")
//    public MessageChannel fileReaderChannel(){
//        MessageChannel channel = new DirectChannel();
//        return new DirectChannel();
//    }


    @Bean(name = "fileReaderChannel")
    public MessageChannel fileReaderChannel() {
        TaskExecutor executor = fileReaderTaskExecutor();
        return new ExecutorChannel(executor);
    }
    @Bean(name = "unRecoverableErrorChannel")
    public MessageChannel unRecoverableErrorChannel()
    {
        return new DirectChannel();
    }

    @Bean(name = "FinalChannel")
    public MessageChannel finalChannel()
    {
        return new DirectChannel();
    }

    @Bean
    public ThreadPoolTaskExecutor fileReaderTaskExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(MaxPoolSize);
        taskExecutor.setQueueCapacity(QueueCapacity);
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        return taskExecutor;
    }


}