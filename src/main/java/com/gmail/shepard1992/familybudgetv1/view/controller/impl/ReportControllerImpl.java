package com.gmail.shepard1992.familybudgetv1.view.controller.impl;

import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TemplateService;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.SaveTemplate;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonFileApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.LoadDto;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class ReportControllerImpl implements ReportController, SaveTemplate {

    private MainApplication mainApp;
    private Stage stage;
    private Service<IncomeDto> incomeService;
    private Service<CostDto> costService;
    private TemplateService templateService;
    private FileUtil fileUtil;
    private File file;

    private final ButtonFileApi addIncomeBtn = MainApplication::addIncomeRow;
    private final ButtonFileApi updateIncomeBtn = MainApplication::updateIncomeRow;
    private final ButtonFileApi deleteIncomeBtn = MainApplication::deleteIncomeRow;

    private final ButtonFileApi addCostBtn = MainApplication::addCostRow;
    private final ButtonFileApi updateCostBtn = MainApplication::updateCostRow;
    private final ButtonFileApi deleteCostBtn = MainApplication::deleteCostRow;

    //Таблица доходов

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

    //Таблица расходов

    @FXML
    private TableView<CostDto> tableCost;

    @FXML
    private TableColumn<CostDto, String> indexCost;

    @FXML
    private TableColumn<CostDto, String> categoryCost;

    @FXML
    private TableColumn<CostDto, String> typeCost;

    @FXML
    private TableColumn<CostDto, String> sumPlanCost;

    @FXML
    private TableColumn<CostDto, String> sumFactCost;

    public ReportControllerImpl() {

    }

    @Autowired
    public ReportControllerImpl(Service<IncomeDto> incomeService, Service<CostDto> costService, FileUtil fileUtil, TemplateService templateService) {
        this.incomeService = incomeService;
        this.costService = costService;
        this.fileUtil = fileUtil;
        this.templateService = templateService;
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;

        LoadDto<IncomeDto> loadIncomeDto = new LoadDto<>(incomeService, tableIncome, file);
        LoadDto<CostDto> loadCostDto = new LoadDto<>(costService, tableCost, file);

        fileUtil.loadDtoData(loadIncomeDto);
        fileUtil.loadDtoData(loadCostDto);
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.stage = dialogStage;
    }

    @FXML
    public void initialize() {
        initializeIncomeTable();
        initializeCostTable();
    }

    private void initializeCostTable() {
        indexCost.setCellValueFactory(cellData -> cellData.getValue().getIndexProperty());
        categoryCost.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        typeCost.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        sumFactCost.setCellValueFactory(cellData -> cellData.getValue().getSumFactProperty());
        sumPlanCost.setCellValueFactory(cellData -> cellData.getValue().getSumPlanProperty());
    }

    private void initializeIncomeTable() {
        indexIncome.setCellValueFactory(cellData -> cellData.getValue().getIndexProperty());
        categoryIncome.setCellValueFactory(cellData -> cellData.getValue().getCategoryProperty());
        typeIncome.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        sumIncome.setCellValueFactory(cellData -> cellData.getValue().getSumFactProperty());
    }

    @Override
    public void addIncomeRow() {
        addIncomeBtn.click(mainApp, file);
        LoadDto<IncomeDto> loadDto = new LoadDto<>(incomeService, tableIncome, file);
        fileUtil.loadDtoData(loadDto);
    }

    @Override
    public void updateIncomeRow() {
        updateIncomeBtn.click(mainApp, file);
        LoadDto<IncomeDto> loadDto = new LoadDto<>(incomeService, tableIncome, file);
        fileUtil.loadDtoData(loadDto);
    }

    @Override
    public void deleteIncomeRow() {
        deleteIncomeBtn.click(mainApp, file);
        LoadDto<IncomeDto> loadDto = new LoadDto<>(incomeService, tableIncome, file);
        fileUtil.loadDtoData(loadDto);
    }

    @Override
    public void addCostRow() {
        addCostBtn.click(mainApp, file);
        LoadDto<CostDto> loadDto = new LoadDto<>(costService, tableCost, file);
        fileUtil.loadDtoData(loadDto);
    }

    @Override
    public void updateCostRow() {
        updateCostBtn.click(mainApp, file);
        LoadDto<CostDto> loadDto = new LoadDto<>(costService, tableCost, file);
        fileUtil.loadDtoData(loadDto);
    }

    @Override
    public void deleteCostRow() {
        deleteCostBtn.click(mainApp, file);
        LoadDto<CostDto> loadDto = new LoadDto<>(costService, tableCost, file);
        fileUtil.loadDtoData(loadDto);
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public void saveTemplate() {
        templateService.saveTemplate(file);
    }
}
