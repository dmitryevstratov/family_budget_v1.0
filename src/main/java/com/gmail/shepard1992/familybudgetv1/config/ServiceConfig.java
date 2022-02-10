package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.repository.api.CreateReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.impl.CreateReportServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.IncomeServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ViewUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public IncomeService getServiceBean(ValidationUtil validationUtil, Repository<Income> repository, MapperUtil mapperUtil, IndexUtil indexUtil) {
        return new IncomeServiceImpl(validationUtil, repository, mapperUtil, indexUtil);
    }

    @Bean
    public ModalViewService getModalViewServiceBean(ApplicationContext context, ViewUtil viewUtil) {
        return new ModalViewServiceImpl(context, viewUtil);
    }

    @Bean
    public CreateReportService getCreateReportServiceBean(CreateReportRepository repository) {
        return new CreateReportServiceImpl(repository);
    }

}
