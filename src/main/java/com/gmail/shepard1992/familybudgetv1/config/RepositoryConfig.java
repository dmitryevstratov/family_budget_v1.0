package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.repository.impl.IncomeRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public Repository<Income> getIncomeRepositoryBean(FileUtil fileUtil) {
        return new IncomeRepositoryImpl(fileUtil);
    }

}
