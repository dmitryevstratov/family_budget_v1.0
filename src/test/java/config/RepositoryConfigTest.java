package config;

import com.gmail.shepard1992.familybudgetv1.repository.api.*;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class RepositoryConfigTest {

    @Bean
    @Primary
    public RepositoryData<Income> getIncomeRepositoryTestBean() {
        return mock(RepositoryData.class);
    }

    @Bean
    @Primary
    public CreateFileReportRepository getCreateReportRepositoryTestBean() {
        return mock(CreateFileReportRepository.class);
    }

    @Bean
    @Primary
    public RepositoryData<Cost> getCostRepositoryTestBean() {
        return mock(RepositoryData.class);
    }

    @Bean
    @Primary
    public OpenFileReportRepository getOpenFileReportRepositoryTestBean() {
        return mock(OpenFileReportRepository.class);
    }

    @Bean
    @Primary
    public TemplateRepository getTemplateRepositoryTestBean() {
        return mock(TemplateRepository.class);
    }

    @Bean
    @Primary
    public OpenYearReportRepository getOpenYearReportRepositoryTestBean(){
        return mock(OpenYearReportRepository.class);
    }

    @Bean
    @Primary
    public ReportRepository getReportRepositoryTestBean(){
        return mock(ReportRepository.class);
    }

}
