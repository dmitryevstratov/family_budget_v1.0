package com.gmail.shepard1992.familybudgetv1.repository.config;

import com.gmail.shepard1992.familybudgetv1.repository.api.*;
import com.gmail.shepard1992.familybudgetv1.repository.impl.*;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ModelRepositoryUtil;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ReportRepositoryFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public RepositoryData<Income> getIncomeRepositoryBean(ReportRepository reportRepository, ModelRepositoryUtil modelRepositoryUtil) {
        return new IncomeRepositoryDataImpl(reportRepository, modelRepositoryUtil);
    }

    @Bean
    public CreateFileReportRepository getCreateReportRepositoryBean(FileUtil fileUtil, ReportRepository repository) {
        return new CreateFileReportRepositoryImpl(fileUtil, repository);
    }

    @Bean
    public ReportRepository getReportRepositoryBean(FileUtil fileUtil, ReportRepositoryFacade facade) {
        return new ReportRepositoryImpl(fileUtil, facade);
    }

    @Bean
    public RepositoryData<Cost> getCostRepositoryBean(ReportRepository reportRepository, ModelRepositoryUtil modelRepositoryUtil) {
        return new CostRepositoryDataImpl(reportRepository, modelRepositoryUtil);
    }

    @Bean
    public OpenFileReportRepository getOpenFileReportRepositoryBean(FileUtil fileUtil) {
        return new OpenFileReportRepositoryImpl(fileUtil);
    }

    @Bean
    public TemplateRepository getTemplateRepositoryBean(FileUtil fileUtil) {
        return new TemplateRepositoryImpl(fileUtil);
    }

    @Bean
    public OpenYearReportRepository getOpenYearReportRepositoryBean(FileUtil fileUtil){
        return new OpenYearReportRepositoryImpl(fileUtil);
    }

}
