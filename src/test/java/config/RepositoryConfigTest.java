package config;

import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.repository.impl.CreateFileReportRepositoryImpl;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class RepositoryConfigTest {

    @Bean
    @Primary
    public Repository<Income> getIncomeRepositoryTestBean() {
        return mock(Repository.class);
    }

    @Bean
    @Primary
    public CreateFileReportRepository getCreateReportRepositoryBean() {
        return mock(CreateFileReportRepository.class);
    }

}
