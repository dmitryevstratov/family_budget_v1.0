package com.gmail.shepard1992.familybudgetv1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        ControllerConfig.class,
        ViewConfig.class,
        ServiceConfig.class,
        RepositoryConfig.class,
        UtilConfig.class
}
)
public class AppConfig {


}
