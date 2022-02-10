package com.gmail.shepard1992.familybudgetv1;

import com.gmail.shepard1992.familybudgetv1.api.ShowModalViewApi;
import com.gmail.shepard1992.familybudgetv1.api.ShowViewApi;
import com.gmail.shepard1992.familybudgetv1.api.income.IncomeActionApi;
import com.gmail.shepard1992.familybudgetv1.api.income.ShowModalIncomeViewApi;
import com.gmail.shepard1992.familybudgetv1.config.AppConfig;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.viewIncomeService.AddRowIncomeModalView;
import com.gmail.shepard1992.familybudgetv1.model.dto.viewIncomeService.DeleteRowIncomeModalView;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.constants.ViewsPath.*;

public class MainApplication extends Application implements ShowViewApi, IncomeActionApi, ShowModalIncomeViewApi, ShowModalViewApi {

    private Stage primaryStage;
    private ViewService viewService;
    private ModalIncomeViewService modalIncomeViewService;
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
        AddRowIncomeModalView dto = new AddRowIncomeModalView(MODAL_ADD_ROW_INCOME_VIEW, incomeDto, file);
        modalIncomeViewService.showAddRowIncomeModalView(dto);
    }

    @Override
    public void showUpdateRowIncomeModalView(IncomeDto incomeDto, File file) {
        AddRowIncomeModalView dto = new AddRowIncomeModalView(MODAL_UPDATE_ROW_INCOME_VIEW, incomeDto, file);
        modalIncomeViewService.showUpdateRowIncomeModalView(dto);
    }

    @Override
    public void showDeleteRowIncomeModalView(String index, File file) {
        DeleteRowIncomeModalView dto = new DeleteRowIncomeModalView(MODAL_DELETE_ROW_INCOME_VIEW, index, file);
        modalIncomeViewService.showDeleteRowIncomeModalView(dto);
    }

    @Override
    public void showModalCreateReportView() {
        modalViewService.showCreateReportModalView(MODAL_CREATE_REPORT_VIEW);
    }

}