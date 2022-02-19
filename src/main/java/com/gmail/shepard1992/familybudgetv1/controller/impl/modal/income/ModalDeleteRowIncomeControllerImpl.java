package com.gmail.shepard1992.familybudgetv1.controller.impl.modal.income;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalDeleteIncomeRowController;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.service.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class ModalDeleteRowIncomeControllerImpl implements ModalDeleteIncomeRowController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private String index;
    private final Service<IncomeDto> incomeService;
    private File file;

    @FXML
    private TextField indexField;

    @Autowired
    public ModalDeleteRowIncomeControllerImpl(Service<IncomeDto> incomeService) {
        this.incomeService = incomeService;
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private boolean handleOk() {
        ServiceDeleteRowDto params = new ServiceDeleteRowDto(
                indexField,
                dialogStage,
                file
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

    @Override
    public void setFile(File file) {
        this.file = file;
    }
}
