package com.gmail.shepard1992.familybudgetv1.view.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = {
        ControllerConfig.class
}
)
public class ViewConfig {


}
