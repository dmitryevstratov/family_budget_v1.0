package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.repository.api.IncomeRepository;
import com.gmail.shepard1992.familybudgetv1.repository.impl.IncomeRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public IncomeRepository getIncomeRepositoryBean(FileUtil fileUtil) {
        return new IncomeRepositoryImpl(fileUtil);
    }

}
