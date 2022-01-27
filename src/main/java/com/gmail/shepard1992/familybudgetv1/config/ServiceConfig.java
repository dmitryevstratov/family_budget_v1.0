package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.repository.api.IncomeRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.service.impl.IncomeServiceImpl;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IncomeService getServiceBean(ValidationUtil validationUtil, IncomeRepository repository, MapperUtil mapperUtil, IndexUtil indexUtil) {
        return new IncomeServiceImpl(validationUtil, repository, mapperUtil, indexUtil);
    }

}
