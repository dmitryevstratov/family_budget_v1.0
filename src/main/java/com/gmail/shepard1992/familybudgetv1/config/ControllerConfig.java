package com.gmail.shepard1992.familybudgetv1.config;

import com.gmail.shepard1992.familybudgetv1.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.ModalCreateReportController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalDeleteRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalUpdateRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.impl.MainControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.ReportControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.modal.ModalCreateReportControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.modal.income.ModalAddRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.modal.income.ModalDeleteRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.controller.impl.modal.income.ModalUpdateRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
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
    public ModalDeleteRowIncomeController getModalDeleteRowIncomeControllerBean(IncomeService incomeService, FileUtil fileUtil) {
        return new ModalDeleteRowIncomeControllerImpl(incomeService, fileUtil);
    }

    @Bean
    public ModalCreateReportController getModalCreateReportControllerBean(CreateReportService service, MapperUtil mapperUtil) {
        return new ModalCreateReportControllerImpl(service, mapperUtil);
    }

}
