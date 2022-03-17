package com.gmail.shepard1992.familybudgetv1.utils.config;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.*;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ReportRepositoryFacade;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class UtilConfig {

    @Bean
    public FileUtil getFileUtilBean(ValueUtil valueUtil, MapperUtil mapperUtil) {
        return new FileUtil(valueUtil, mapperUtil);
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

    @Bean
    public ValueUtil getValueUtilBean() {
        return new ValueUtil();
    }

    @Bean
    public DeleteRowUtil<Income> getDeleteIncomeRowUtilBean(ValidationUtil validationUtil, RepositoryData<Income> repositoryData) {
        return new DeleteRowUtil<>(validationUtil, repositoryData);
    }

    @Bean
    public DeleteRowUtil<Cost> getDeleteCostRowUtilBean(ValidationUtil validationUtil, RepositoryData<Cost> repositoryData) {
        return new DeleteRowUtil<>(validationUtil, repositoryData);
    }

}
