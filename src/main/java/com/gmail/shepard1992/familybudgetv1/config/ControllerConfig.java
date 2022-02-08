package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.controller.api.income.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.api.income.ModalUpdateRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.controller.impl.*;
import com.gmail.shepard1992.familybudgetv1.controller.impl.modal.income.ModalAddRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.modal.income.ModalDeleteRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.modal.income.ModalUpdateRowIncomeControllerImpl;
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
    public ModalUpdateRowIncomeController getModalUpdateRowIncomeControllerBean(IncomeService incomeService, FileUtil fileUtil) {
        return new ModalUpdateRowIncomeControllerImpl(incomeService, fileUtil);
    }

    @Bean
    public ModalDeleteRowIncomeControllerImpl getModalDeleteRowIncomeControllerBean(IncomeService incomeService, FileUtil fileUtil) {
        return new ModalDeleteRowIncomeControllerImpl(incomeService, fileUtil);
    }

}
