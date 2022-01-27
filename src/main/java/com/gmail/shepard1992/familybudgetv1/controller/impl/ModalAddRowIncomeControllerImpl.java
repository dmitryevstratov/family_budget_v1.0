package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceAddRowDto;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ModalAddRowIncomeControllerImpl implements ModalAddRowIncomeController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private IncomeDto incomeDto;
    private final IncomeService incomeService;
    private final FileUtil fileUtil;

    @FXML
    private TextField category;

    @FXML
    private TextField type;

    @FXML
    private TextField sum;

    @Autowired
    public ModalAddRowIncomeControllerImpl(IncomeService incomeService, FileUtil fileUtil) {
        this.incomeService = incomeService;
        this.fileUtil = fileUtil;
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
        ParamsForServiceAddRowDto params = new ParamsForServiceAddRowDto(
                category,
                type,
                sum,
                dialogStage,
                fileUtil.getFile()
        );
        return incomeService.addRow(params);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

}
