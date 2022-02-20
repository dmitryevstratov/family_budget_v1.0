package config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class ApplicationContextConfigTest {

    @Bean
    @Primary
    public ApplicationContext getAppMockBean() {
        return mock(ApplicationContext.class);
    }

}
