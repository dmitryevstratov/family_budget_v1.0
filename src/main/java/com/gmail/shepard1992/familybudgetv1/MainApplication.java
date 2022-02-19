package com.gmail.shepard1992.familybudgetv1;

import com.gmail.shepard1992.familybudgetv1.api.ShowModalViewApi;
import com.gmail.shepard1992.familybudgetv1.api.ShowViewApi;
import com.gmail.shepard1992.familybudgetv1.api.cost.CostActionApi;
import com.gmail.shepard1992.familybudgetv1.api.cost.ShowModalCostViewApi;
import com.gmail.shepard1992.familybudgetv1.api.income.IncomeActionApi;
import com.gmail.shepard1992.familybudgetv1.api.income.ShowModalIncomeViewApi;
import com.gmail.shepard1992.familybudgetv1.config.AppConfig;
import com.gmail.shepard1992.familybudgetv1.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.cost.AddRowCostModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.income.AddRowIncomeModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalCostViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.constants.ViewsPath.*;

public class MainApplication extends Application implements ShowViewApi, IncomeActionApi, ShowModalIncomeViewApi, ShowModalViewApi, CostActionApi, ShowModalCostViewApi {

    private Stage primaryStage;
    private ViewService viewService;
    private ModalIncomeViewService modalIncomeViewService;
    private ModalCostViewService modalCostViewService;
    private ModalViewService modalViewService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        this.primaryStage = primaryStage;

        this.viewService = context.getBean(ViewService.class);
        this.viewService.setMainApp(this);
        this.viewService.setPrimaryStage(primaryStage);

        this.modalIncomeViewService = context.getBean(ModalIncomeViewService.class);
        this.modalCostViewService = context.getBean(ModalCostViewService.class);

        this.modalViewService = context.getBean(ModalViewService.class);
        this.modalViewService.setMainApp(this);
        this.modalViewService.setPrimaryStage(primaryStage);

        showRootView();
    }

    @Override
    public void addIncomeRow(File file) {
        IncomeDto incomeDto = new IncomeDto.IncomeDtoBuilder().build();
        showAddRowIncomeModalView(incomeDto, file);
    }

    @Override
    public void updateIncomeRow(File file) {
        IncomeDto incomeDto = new IncomeDto.IncomeDtoBuilder().build();
        showUpdateRowIncomeModalView(incomeDto, file);
    }

    @Override
    public void deleteIncomeRow(File file) {
        String index = "";
        showDeleteRowIncomeModalView(index, file);
    }

    @Override
    public void showRootView() {
        viewService.showRootView(START_VIEW);
    }

    @Override
    public void showReportView(File file) {
        viewService.showReportView(REPORT_VIEW, file);
    }

    @Override
    public void showAddRowIncomeModalView(IncomeDto incomeDto, File file) {
        AddRowIncomeModalViewDto dto = new AddRowIncomeModalViewDto(MODAL_ADD_ROW_INCOME_VIEW, incomeDto, file);
        modalIncomeViewService.showAddRowIncomeModalView(dto);
    }

    @Override
    public void showUpdateRowIncomeModalView(IncomeDto incomeDto, File file) {
        AddRowIncomeModalViewDto dto = new AddRowIncomeModalViewDto(MODAL_UPDATE_ROW_INCOME_VIEW, incomeDto, file);
        modalIncomeViewService.showUpdateRowIncomeModalView(dto);
    }

    @Override
    public void showDeleteRowIncomeModalView(String index, File file) {
        DeleteRowModalViewDto dto = new DeleteRowModalViewDto(MODAL_DELETE_ROW_INCOME_VIEW, index, file);
        modalIncomeViewService.showDeleteRowIncomeModalView(dto);
    }

    @Override
    public void showModalCreateReportView() {
        modalViewService.showCreateReportModalView(MODAL_CREATE_REPORT_VIEW);
    }

    @Override
    public void addCostRow(File file) {
        CostDto costDto = new CostDto.CostDtoBuilder().build();
        showAddRowIncomeModalView(costDto, file);
    }

    @Override
    public void updateCostRow(File file) {
        CostDto costDto = new CostDto.CostDtoBuilder().build();
        showUpdateRowCostModalView(costDto, file);
    }

    @Override
    public void deleteCostRow(File file) {
        String index = "";
        showDeleteRowCostModalView(index, file);
    }

    @Override
    public void showAddRowIncomeModalView(CostDto costDto, File file) {
        AddRowCostModalViewDto addRowCostModalViewDto = new AddRowCostModalViewDto(MODAL_ADD_ROW_COST_VIEW, costDto, file);
        modalCostViewService.showAddRowCostModalView(addRowCostModalViewDto);
    }

    @Override
    public void showUpdateRowCostModalView(CostDto costDto, File file) {
        AddRowCostModalViewDto addRowCostModalViewDto = new AddRowCostModalViewDto(MODAL_UPDATE_ROW_COST_VIEW, costDto, file);
        modalCostViewService.showUpdateRowCostModalView(addRowCostModalViewDto);
    }

    @Override
    public void showDeleteRowCostModalView(String index, File file) {
        DeleteRowModalViewDto dto = new DeleteRowModalViewDto(MODAL_DELETE_ROW_COST_VIEW, index, file);
        modalCostViewService.showDeleteRowCostModalView(dto);
    }
}