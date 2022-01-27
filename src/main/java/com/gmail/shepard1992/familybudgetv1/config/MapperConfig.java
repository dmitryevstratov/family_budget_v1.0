package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {

    @Bean
    public MapperUtil getMapperBean() {
        return new MapperUtil();
    }

}
