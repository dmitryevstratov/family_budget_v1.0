package com.gmail.shepard1992.familybudgetv1.view.config;

import com.gmail.shepard1992.familybudgetv1.service.api.*;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.YearReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalCreateReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalLoadTemplateController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalOpenReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalOpenYearReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.cost.ModalAddRowCostController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.cost.ModalDeleteCostRowController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.cost.ModalUpdateRowCostController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.income.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.income.ModalDeleteIncomeRowController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.income.ModalUpdateRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.MainControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.ReportControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.YearReportControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.ModalCreateReportControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.ModalLoadTemplateControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.ModalOpenReportControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.ModalOpenYearReportControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.cost.ModalAddRowCostControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.cost.ModalDeleteRowCostControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.cost.ModalUpdateRowCostControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.income.ModalAddRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.income.ModalDeleteRowIncomeControllerImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.income.ModalUpdateRowIncomeControllerImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ViewConfig {

    @Bean
    public MainController getMainControllerBean() {
        return new MainControllerImpl();
    }

    @Bean
    public ReportController getReportControllerBean(Service<IncomeDto> incomeService, Service<CostDto> costService, FileUtil fileUtil, TemplateService templateService) {
        return new ReportControllerImpl(incomeService, costService, fileUtil, templateService);
    }

    @Bean
    public ModalAddRowIncomeController getModalAddRowIncomeControllerBean(Service<IncomeDto> incomeService) {
        return new ModalAddRowIncomeControllerImpl(incomeService);
    }

    @Bean
    public ModalUpdateRowIncomeController getModalUpdateRowIncomeControllerBean(Service<IncomeDto> incomeService) {
        return new ModalUpdateRowIncomeControllerImpl(incomeService);
    }

    @Bean
    public ModalDeleteIncomeRowController getModalDeleteRowIncomeControllerBean(Service<IncomeDto> incomeService) {
        return new ModalDeleteRowIncomeControllerImpl(incomeService);
    }

    @Bean
    public ModalCreateReportController getModalCreateReportControllerBean(CreateReportService service, MapperUtil mapperUtil) {
        return new ModalCreateReportControllerImpl(service, mapperUtil);
    }

    @Bean
    public ModalAddRowCostController getModalAddRowCostControllerBean(Service<CostDto> service) {
        return new ModalAddRowCostControllerImpl(service);
    }

    @Bean
    public ModalUpdateRowCostController getModalUpdateRowCostControllerBean(Service<CostDto> costService) {
        return new ModalUpdateRowCostControllerImpl(costService);
    }

    @Bean
    public ModalDeleteCostRowController getModalDeleteRowCostControllerBean(Service<CostDto> costService) {
        return new ModalDeleteRowCostControllerImpl(costService);
    }

    @Bean
    public ModalOpenReportController getModalOpenReportControllerBean(OpenReportService service) {
        return new ModalOpenReportControllerImpl(service);
    }

    @Bean
    public ModalLoadTemplateController getModalLoadTemplateControllerBean(TemplateService service) {
        return new ModalLoadTemplateControllerImpl(service);
    }

    @Bean
    public ModalOpenYearReportController getModalOpenYearReportControllerBean(OpenYearReportService service) {
        return new ModalOpenYearReportControllerImpl(service);
    }

    @Bean
    public YearReportController getYearReportControllerBean() {
        return new YearReportControllerImpl();
    }

}
