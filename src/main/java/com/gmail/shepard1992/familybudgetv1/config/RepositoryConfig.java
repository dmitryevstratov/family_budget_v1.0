package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.model.Cost;
import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.repository.impl.CostRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.repository.impl.CreateFileReportRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.repository.impl.IncomeRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.repository.impl.ReportRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public Repository<Income> getIncomeRepositoryBean(ReportRepository reportRepository) {
        return new IncomeRepositoryImpl(reportRepository);
    }

    @Bean
    public CreateFileReportRepository getCreateReportRepositoryBean(FileUtil fileUtil, ReportRepository repository) {
        return new CreateFileReportRepositoryImpl(fileUtil, repository);
    }

    @Bean
    public ReportRepository getReportRepositoryBean(FileUtil fileUtil) {
        return new ReportRepositoryImpl(fileUtil);
    }

    @Bean
    public Repository<Cost> getCostRepositoryBean(ReportRepository reportRepository) {
        return new CostRepositoryImpl(reportRepository);
    }

}
