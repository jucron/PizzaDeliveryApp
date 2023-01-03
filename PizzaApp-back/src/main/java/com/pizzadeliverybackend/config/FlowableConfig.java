package com.pizzadeliverybackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FlowableConfig {
    @Bean
    void configHistoryLevel() {
//        ProcessEngineConfiguration
//                .createStandaloneInMemProcessEngineConfiguration()
//                .setHistoryLevel(HistoryLevel.AUDIT);
    }
}
