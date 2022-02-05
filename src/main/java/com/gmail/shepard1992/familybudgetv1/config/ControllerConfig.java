package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.controller.api.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.controller.impl.MainControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.ModalAddRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.ModalDeleteRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.ReportControllerImpl;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    @Bean
    public MainController getMainControllerBean() {
        return new MainControllerImpl();
    }

    @Bean
    public ReportController getReportControllerBean(IncomeService incomeService, FileUtil fileUtil) {
        return new ReportControllerImpl(incomeService, fileUtil);
    }

    @Bean
    public ModalAddRowIncomeController getModalAddRowIncomeControllerBean(IncomeService incomeService, FileUtil fileUtil) {
        return new ModalAddRowIncomeControllerImpl(incomeService, fileUtil);
    }

    @Bean
    public ModalDeleteRowIncomeControllerImpl getModalDeleteRowIncomeControllerBean(IncomeService incomeService, FileUtil fileUtil) {
        return new ModalDeleteRowIncomeControllerImpl(incomeService, fileUtil);
    }

}
