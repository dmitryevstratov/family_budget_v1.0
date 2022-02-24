package com.gmail.shepard1992.familybudgetv1.controller.impl.modal.cost;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.cost.ModalUpdateRowCostController;
import com.gmail.shepard1992.familybudgetv1.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.service.ServiceNewRowDto;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class ModalUpdateRowCostControllerImpl implements ModalUpdateRowCostController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private CostDto costDto;
    private final Service<CostDto> costService;
    private File file;

    @FXML
    private TextField index;

    @FXML
    private TextField category;

    @FXML
    private TextField type;

    @FXML
    private TextField sumPlan;

    @FXML
    private TextField sumFact;

    @Autowired
    public ModalUpdateRowCostControllerImpl(Service<CostDto> costService) {
        this.costService = costService;
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
    public void setDto(CostDto costDto) {
        this.costDto = costDto;
    }

    @FXML
    private boolean handleOk() {
        ServiceNewRowDto params = new ServiceNewRowDto.ServiceNewRowDtoBuilder()
                .setIndex(index)
                .setCategory(category)
                .setType(type)
                .setSumFact(sumFact)
                .setSumPlan(sumPlan)
                .setDialogStage(dialogStage)
                .setFile(file)
                .build();
        return costService.updateRow(params);
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

}
