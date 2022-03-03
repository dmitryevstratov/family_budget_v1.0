package config;

import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@Configuration
public class FileUtilConfigTest {

    @Bean
    @Primary
    public FileUtil getFileUtilTestBean(){
        return mock(FileUtil.class);
    }

}
