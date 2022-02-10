package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalIncomeViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.ViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.utils.ViewUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig {

    @Bean
    public ViewService getViewBean(ApplicationContext context, ViewUtil viewUtil) {
        return new ViewServiceImpl(context, viewUtil);
    }

    @Bean
    public ModalIncomeViewService getModalIncomeViewServiceBean(ApplicationContext context) {
        return new ModalIncomeViewServiceImpl(context);
    }

}
