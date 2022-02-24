package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.service.api.ModalCostViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.ViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalCostViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalIncomeViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig {

    @Bean
    public ViewService getViewBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ViewServiceImpl(context, viewFacade);
    }

    @Bean
    public ModalIncomeViewService getModalIncomeViewServiceBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ModalIncomeViewServiceImpl(context, viewFacade);
    }

    @Bean
    public ModalCostViewService getModalCostViewServiceBean(ApplicationContext context, ViewFacade viewFacade) {
        return new ModalCostViewServiceImpl(context, viewFacade);
    }

}
