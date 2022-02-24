package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.model.Cost;
import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.impl.CostServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.CreateReportServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.IncomeServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.utils.TotalServiceUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public Service<IncomeDto> getIncomeServiceBean(ValidationUtil validationUtil, Repository<Income> repository, MapperUtil mapperUtil, IndexUtil<IncomeDto> indexUtil, TotalServiceUtil totalServiceUtil) {
        return new IncomeServiceImpl(validationUtil, repository, mapperUtil, indexUtil, totalServiceUtil);
    }

    @Bean
    public Service<CostDto> getCostServiceBean(ValidationUtil validationUtil, Repository<Cost> repository, MapperUtil mapperUtil, IndexUtil<CostDto> indexUtil, TotalServiceUtil totalServiceUtil) {
        return new CostServiceImpl(validationUtil, repository, mapperUtil, indexUtil, totalServiceUtil);
    }

    @Bean
    public ModalViewService getModalViewServiceBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ModalViewServiceImpl(context, viewFacade);
    }

    @Bean
    public CreateReportService getCreateReportServiceBean(CreateFileReportRepository repository) {
        return new CreateReportServiceImpl(repository);
    }

}
