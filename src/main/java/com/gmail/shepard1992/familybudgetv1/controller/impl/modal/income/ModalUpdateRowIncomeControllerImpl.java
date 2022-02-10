package com.gmail.shepard1992.familybudgetv1.controller.impl.modal.income;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalUpdateRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.incomeService.ServiceUpdateRowDto;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class ModalUpdateRowIncomeControllerImpl implements ModalUpdateRowIncomeController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private IncomeDto incomeDto;
    private final IncomeService incomeService;
    private File file;

    @FXML
    private TextField index;

    @FXML
    private TextField category;

    @FXML
    private TextField type;

    @FXML
    private TextField sum;

    @Autowired
    public ModalUpdateRowIncomeControllerImpl(IncomeService incomeService) {
        this.incomeService = incomeService;
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void setIncome(IncomeDto incomeDto) {
        this.incomeDto = incomeDto;
    }

    @FXML
    private boolean handleOk() {
        ServiceUpdateRowDto params = new ServiceUpdateRowDto(
                index,
                category,
                type,
                sum,
                dialogStage,
                file
        );
        return incomeService.updateRow(params);
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

}
