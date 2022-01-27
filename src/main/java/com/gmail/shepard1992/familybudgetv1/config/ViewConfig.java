package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import com.gmail.shepard1992.familybudgetv1.service.impl.ModalViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.ViewServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig {

    @Bean
    public ViewService getViewBean(ApplicationContext context) {
        return new ViewServiceImpl(context);
    }

    @Bean
    public ModalViewService getModalViewServiceBean(ApplicationContext context) {
        return new ModalViewServiceImpl(context);
    }

}
