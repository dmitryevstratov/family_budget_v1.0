package com.gmail.shepard1992.familybudgetv1.controller.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.ModalDeleteRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ModalDeleteRowIncomeControllerImpl implements ModalDeleteRowIncomeController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private String index;
    private final IncomeService incomeService;
    private final FileUtil fileUtil;

    @FXML
    private TextField indexField;

    @Autowired
    public ModalDeleteRowIncomeControllerImpl(IncomeService incomeService, FileUtil fileUtil) {
        this.incomeService = incomeService;
        this.fileUtil = fileUtil;
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private boolean handleOk() {
        ParamsForServiceDeleteRowDto params = new ParamsForServiceDeleteRowDto(
                indexField,
                dialogStage,
                fileUtil.getFile()
        );
        return incomeService.deleteRow(params);
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setIndex(String index) {
        this.index = index;
    }
}
