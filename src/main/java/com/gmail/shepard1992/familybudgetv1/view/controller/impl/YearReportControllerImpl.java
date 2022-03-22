package com.gmail.shepard1992.familybudgetv1.view.controller.impl;

import com.gmail.shepard1992.familybudgetv1.service.api.MonthReportService;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.YearReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonApi;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonBackToMainViewApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.MonthReportDto;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class YearReportControllerImpl implements YearReportController, ButtonBackToMainViewApi {

    private File[] files;
    private MainApplication mainApp;
    private Stage stage;
    private final MonthReportService service;

    private final ButtonApi backToMainView = MainApplication::showRootView;
    private final ButtonApi btnShowModalHelpYearReportTable = MainApplication::showModalHelpYearReportTableView;

    //Таблица

    @FXML
    private TableView<MonthReportDto> table;

    @FXML
    private TableColumn<MonthReportDto, String> month;

    @FXML
    private TableColumn<MonthReportDto, String> income;

    @FXML
    private TableColumn<MonthReportDto, String> cost;

    @FXML
    private TableColumn<MonthReportDto, String> total;

    @FXML
    private TableColumn<MonthReportDto, String> totalPercent;

    @FXML
    private TableColumn<MonthReportDto, String> bigPurchases;

    @Autowired
    public YearReportControllerImpl(MonthReportService service) {
        this.service = service;
    }

    @Override
    public void backToMainView() {
        backToMainView.click(mainApp);
    }

    @FXML
    public void initialize() {
        month.setCellValueFactory(cellData -> cellData.getValue().getMonthProperty());
        income.setCellValueFactory(cellData -> cellData.getValue().getIncomeProperty());
        cost.setCellValueFactory(cellData -> cellData.getValue().getCostProperty());
        total.setCellValueFactory(cellData -> cellData.getValue().getTotalProperty());
        totalPercent.setCellValueFactory(cellData -> cellData.getValue().getTotalPercentProperty());
        bigPurchases.setCellValueFactory(cellData -> cellData.getValue().getMajorPurchasesProperty());
    }

    @Override
    public void setFiles(File[] files) {
        this.files = files;
    }

    @FXML
    @Override
    public void showModalHelpYearReportTable() {
        btnShowModalHelpYearReportTable.click(mainApp);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
        service.loadMonthReport(files, table);
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.stage = dialogStage;
    }

}
