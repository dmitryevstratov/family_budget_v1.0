package com.gmail.shepard1992.familybudgetv1;

import com.gmail.shepard1992.familybudgetv1.api.income.IncomeActionApi;
import com.gmail.shepard1992.familybudgetv1.api.income.ShowModalIncomeViewApi;
import com.gmail.shepard1992.familybudgetv1.api.ShowViewApi;
import com.gmail.shepard1992.familybudgetv1.config.AppConfig;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static com.gmail.shepard1992.familybudgetv1.constants.ViewsPath.*;

public class MainApplication extends Application implements ShowViewApi, IncomeActionApi, ShowModalIncomeViewApi {

    private Stage primaryStage;
    private ViewService viewService;
    private ModalViewService modalViewService;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        this.primaryStage = primaryStage;
        this.viewService = context.getBean(ViewService.class);
        this.modalViewService = context.getBean(ModalViewService.class);
        this.viewService.setMainApp(this);
        this.viewService.setPrimaryStage(primaryStage);
        showRootView();
    }

    @Override
    public void addIncomeRow() {
        IncomeDto incomeDto = new IncomeDto.IncomeDtoBuilder().build();
        showAddRowIncomeModalView(incomeDto);
    }

    @Override
    public void updateIncomeRow() {
        IncomeDto incomeDto = new IncomeDto.IncomeDtoBuilder().build();
        showUpdateRowIncomeModalView(incomeDto);
    }

    @Override
    public void deleteIncomeRow() {
        String index = new String();
        showDeleteRowIncomeModalView(index);
    }

    @Override
    public void showRootView() {
        viewService.showRootView(START_VIEW);
    }

    @Override
    public void showReportView() {
        viewService.showReportView(REPORT_VIEW);
    }

    @Override
    public void showAddRowIncomeModalView(IncomeDto incomeDto) {
        modalViewService.showAddRowIncomeModalView(MODAL_ADD_ROW_INCOME_VIEW, incomeDto);
    }

    @Override
    public void showUpdateRowIncomeModalView(IncomeDto incomeDto) {
        modalViewService.showUpdateRowIncomeModalView(MODAL_UPDATE_ROW_INCOME_VIEW, incomeDto);
    }

    @Override
    public void showDeleteRowIncomeModalView(String index) {
        modalViewService.showDeleteRowIncomeModalView(MODAL_DELETE_ROW_INCOME_VIEW, index);
    }
}