package com.gmail.shepard1992.familybudgetv1;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.view.config.ViewConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        ViewConfig.class,
        ServiceConfig.class,
        RepositoryConfig.class,
        UtilConfig.class
}
)
public class AppConfig {


}
