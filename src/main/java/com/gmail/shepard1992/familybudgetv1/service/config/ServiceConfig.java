package com.gmail.shepard1992.familybudgetv1.service.config;

import com.gmail.shepard1992.familybudgetv1.repository.api.*;
import com.gmail.shepard1992.familybudgetv1.service.api.*;
import com.gmail.shepard1992.familybudgetv1.service.impl.*;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.ViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalCostViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalIncomeViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalTemplateViewViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.*;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public Service<IncomeDto> getIncomeServiceBean(ValidationUtil validationUtil, RepositoryData<Income> repositoryData, MapperUtil mapperUtil, IndexUtil<IncomeDto> indexUtil, TotalServiceUtil totalServiceUtil) {
        return new IncomeServiceImpl(validationUtil, repositoryData, mapperUtil, indexUtil, totalServiceUtil);
    }

    @Bean
    public Service<CostDto> getCostServiceBean(ValidationUtil validationUtil, RepositoryData<Cost> repositoryData, MapperUtil mapperUtil, IndexUtil<CostDto> indexUtil, TotalServiceUtil totalServiceUtil) {
        return new CostServiceImpl(validationUtil, repositoryData, mapperUtil, indexUtil, totalServiceUtil);
    }

    @Bean
    public ModalViewService getModalViewServiceBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ModalViewServiceImpl(context, viewFacade);
    }

    @Bean
    public CreateReportService getCreateReportServiceBean(CreateFileReportRepository repository, ValidationUtil validationUtil) {
        return new CreateReportServiceImpl(repository, validationUtil);
    }

    @Bean
    public ViewService getViewBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ViewServiceImpl(context, viewFacade);
    }

    @Bean
    public ModalIncomeViewService getModalIncomeViewServiceBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ModalIncomeViewServiceImpl(context, viewFacade);
    }

    @Bean
    public ModalCostViewService getModalCostViewServiceBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ModalCostViewServiceImpl(context, viewFacade);
    }

    @Bean
    public OpenReportService getOpenReportServiceBean(ValidationUtil validationUtil, OpenFileReportRepository repository) {
        return new OpenReportServiceImpl(validationUtil, repository);
    }

    @Bean
    public TemplateService getTemplateServiceBean(TemplateRepository repository, RepositoryData<Income> incomeRepositoryData, RepositoryData<Cost> costRepositoryData, ValidationUtil validationUtil) {
        return new TemplateServiceImpl(repository, incomeRepositoryData, costRepositoryData, validationUtil);
    }

    @Bean
    public ModalTemplateViewService getModalTemplateServiceBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ModalTemplateViewViewServiceImpl(context, viewFacade);
    }

    @Bean
    public OpenYearReportService getOpenYearReportServiceBean(ValidationUtil validationUtil, OpenYearReportRepository reportRepository) {
        return new OpenYearReportServiceImpl(validationUtil, reportRepository);
    }

    @Bean
    MonthReportService getMonthReportServiceBean(ReportRepository repository, FileUtil fileUtil) {
        return new MonthReportServiceImpl(repository, fileUtil);
    }

}
