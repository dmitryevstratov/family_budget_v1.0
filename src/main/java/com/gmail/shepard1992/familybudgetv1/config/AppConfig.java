package com.gmail.shepard1992.familybudgetv1.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        ControllerConfig.class,
        ViewConfig.class,
        ServiceConfig.class,
        ValidationConfig.class,
        RepositoryConfig.class,
        MapperConfig.class,
        IndexConfig.class,
        FileConfig.class
}
)
public class AppConfig {


}
