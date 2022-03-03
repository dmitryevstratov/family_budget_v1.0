package config;

import com.gmail.shepard1992.familybudgetv1.repository.api.TemplateRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.TemplateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class ServiceConfigTest {

    @Bean
    @Primary
    public TemplateService getTemplateServiceTestBean(TemplateRepository repository) {
        return mock(TemplateService.class);
    }

}
