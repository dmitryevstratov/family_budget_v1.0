package com.gmail.shepard1992.familybudgetv1.view.mainApp;

import com.gmail.shepard1992.familybudgetv1.AppConfig;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalCostViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractAddRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.view.cost.AddRowCostModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.view.income.AddRowIncomeModalViewDto;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.api.ShowModalViewApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.api.ShowViewApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.api.cost.CostActionApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.api.cost.ShowModalCostViewApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.api.income.IncomeActionApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.api.income.ShowModalIncomeViewApi;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.view.constants.ViewsPath.*;

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
        this.viewService.setDialogStage(primaryStage);

        this.modalIncomeViewService = context.getBean(ModalIncomeViewService.class);
        this.modalCostViewService = context.getBean(ModalCostViewService.class);

        this.modalViewService = context.getBean(ModalViewService.class);
        this.modalViewService.setMainApp(this);
        this.modalViewService.setDialogStage(primaryStage);

        showRootView();
    }

    //Кнопки для таблицы доходов

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

    //Кнопки для таблицы расходов

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

    //Показать окна

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
        AbstractAddRowModalViewDto dto = new AddRowIncomeModalViewDto(MODAL_ADD_ROW_INCOME_VIEW, file, incomeDto);
        modalIncomeViewService.showAddRowIncomeModalView(dto);
    }

    @Override
    public void showUpdateRowIncomeModalView(IncomeDto incomeDto, File file) {
        AbstractAddRowModalViewDto dto = new AddRowIncomeModalViewDto(MODAL_UPDATE_ROW_INCOME_VIEW, file, incomeDto);
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
    public void showModalOpenReportView() {
        modalViewService.showOpenReportModalView(MODAL_OPEN_REPORT_VIEW);
    }

    @Override
    public void showAddRowIncomeModalView(CostDto costDto, File file) {
        AbstractAddRowModalViewDto addRowCostModalViewDto = new AddRowCostModalViewDto(MODAL_ADD_ROW_COST_VIEW, file, costDto);
        modalCostViewService.showAddRowCostModalView(addRowCostModalViewDto);
    }

    @Override
    public void showUpdateRowCostModalView(CostDto costDto, File file) {
        AbstractAddRowModalViewDto addRowCostModalViewDto = new AddRowCostModalViewDto(MODAL_UPDATE_ROW_COST_VIEW, file, costDto);
        modalCostViewService.showUpdateRowCostModalView(addRowCostModalViewDto);
    }

    @Override
    public void showDeleteRowCostModalView(String index, File file) {
        DeleteRowModalViewDto dto = new DeleteRowModalViewDto(MODAL_DELETE_ROW_COST_VIEW, index, file);
        modalCostViewService.showDeleteRowCostModalView(dto);
    }

}