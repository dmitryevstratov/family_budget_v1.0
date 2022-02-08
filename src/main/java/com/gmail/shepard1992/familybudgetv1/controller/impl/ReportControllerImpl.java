package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.controller.buttons.api.ButtonApi;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ReportControllerImpl implements ReportController {

    private MainApplication mainApp;
    private IncomeService incomeService;
    private FileUtil fileUtil;

    private final ButtonApi addIncomeBtn = MainApplication::addIncomeRow;
    private final ButtonApi updateIncomeBtn = MainApplication::updateIncomeRow;
    private final ButtonApi deleteIncomeBtn = MainApplication::deleteIncomeRow;

    @FXML
    private TableView<IncomeDto> tableIncome;

    @FXML
    private TableColumn<IncomeDto, String> indexIncome;

    @FXML
    private TableColumn<IncomeDto, String> categoryIncome;

    @FXML
    private TableColumn<IncomeDto, String> typeIncome;

    @FXML
    private TableColumn<IncomeDto, String> sumIncome;

    public ReportControllerImpl() {

    }

    @Autowired
    public ReportControllerImpl(IncomeService incomeService, FileUtil fileUtil) {
        this.incomeService = incomeService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
        fileUtil.loadIncomeDtoData(incomeService, tableIncome);
    }

    @FXML
    public void initialize() {
        indexIncome.setCellValueFactory(cellData -> cellData.getValue().getIndexProperty());
        categoryIncome.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        typeIncome.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        sumIncome.setCellValueFactory(cellData -> cellData.getValue().getSumProperty());
    }

    @Override
    public void addIncomeRow() {
        addIncomeBtn.click(mainApp);
        fileUtil.loadIncomeDtoData(incomeService, tableIncome);
    }

    @Override
    public void updateIncomeRow() {
        updateIncomeBtn.click(mainApp);
        fileUtil.loadIncomeDtoData(incomeService, tableIncome);
    }

    @Override
    public void deleteIncomeRow() {
        deleteIncomeBtn.click(mainApp);
        fileUtil.loadIncomeDtoData(incomeService, tableIncome);
    }

}
