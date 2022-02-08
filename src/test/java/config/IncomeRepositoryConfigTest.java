package config;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;

@Configuration
public class IncomeRepositoryConfigTest {

    @Bean
    public Repository getIncomeRepositoryBean(FileUtil fileUtil) {
        return mock(Repository.class);
    }

}
