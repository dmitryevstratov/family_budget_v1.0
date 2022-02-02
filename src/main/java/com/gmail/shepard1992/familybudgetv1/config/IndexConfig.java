package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class IndexConfig {

    @Bean
    @Scope("singleton")
    public IndexUtil getIndexBean() {
        return new IndexUtil();
    }

}
