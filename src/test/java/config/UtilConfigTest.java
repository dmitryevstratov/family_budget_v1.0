package config;

import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class UtilConfigTest {

    @Bean
    @Primary
    public ViewFacade getViewFacadeTestBean() {
        return mock(ViewFacade.class);
    }

}
