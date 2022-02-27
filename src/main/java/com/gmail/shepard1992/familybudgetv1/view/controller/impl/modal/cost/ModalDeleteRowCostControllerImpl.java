package com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal.cost;

import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.cost.ModalDeleteCostRowController;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceDeleteRowDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class ModalDeleteRowCostControllerImpl implements ModalDeleteCostRowController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private String index;
    private final Service<CostDto> costService;
    private File file;

    @FXML
    private TextField indexField;

    @Autowired
    public ModalDeleteRowCostControllerImpl(Service<CostDto> incomeService) {
        this.costService = incomeService;
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
                file);
        return costService.deleteRow(params);
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
