package com.gmail.shepard1992.familybudgetv1.repository.config;

import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.repository.impl.CostRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.repository.impl.CreateFileReportRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.repository.impl.IncomeRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.repository.impl.ReportRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ModelRepositoryUtil;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ReportRepositoryFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public Repository<Income> getIncomeRepositoryBean(ReportRepository reportRepository, ModelRepositoryUtil modelRepositoryUtil) {
        return new IncomeRepositoryImpl(reportRepository, modelRepositoryUtil);
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
    public Repository<Cost> getCostRepositoryBean(ReportRepository reportRepository, ModelRepositoryUtil modelRepositoryUtil) {
        return new CostRepositoryImpl(reportRepository, modelRepositoryUtil);
    }

}
