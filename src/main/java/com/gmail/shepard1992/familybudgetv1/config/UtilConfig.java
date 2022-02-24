package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.*;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ReportRepositoryFacade;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UtilConfig {

    @Bean
    public FileUtil getFileUtilBean() {
        return new FileUtil();
    }

    @Bean
    @Scope("singleton")
    public IndexUtil<IncomeDto> getIndexIncomeDtoBean() {
        return new IndexUtil<>();
    }

    @Bean
    @Scope("singleton")
    public IndexUtil<CostDto> getIndexCostDtoBean() {
        return new IndexUtil<>();
    }

    @Bean
    public MapperUtil getMapperBean() {
        return new MapperUtil();
    }

    @Bean
    public ValidationUtil getValidationBean() {
        return new ValidationUtil();
    }

    @Bean
    public ViewFacade getViewFacadeBean() {
        return new ViewFacade();
    }

    @Bean
    public ModelRepositoryUtil getModelRepositoryUtil() {
        return new ModelRepositoryUtil();
    }

    @Bean
    public ReportRepositoryFacade getReportRepositoryFacadeBean() {
        return new ReportRepositoryFacade();
    }

    @Bean
    public TotalServiceUtil getTotalServiceUtilBean() {
        return new TotalServiceUtil();
    }

}
