package com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal;

import com.gmail.shepard1992.familybudgetv1.service.api.OpenReportService;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalOpenReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonFileApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.OpenFileDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class ModalOpenReportControllerImpl implements ModalOpenReportController {

    private MainApplication mainApp;
    private OpenReportService service;
    private Stage dialogStage;
    private final FileChooser fileChooser = new FileChooser();
    private File dir;
    private OpenFileDto dto;

    private final ButtonFileApi openReport = MainApplication::showReportView;

    @FXML
    private TextField chooseFileField;

    public ModalOpenReportControllerImpl() {
    }

    @Autowired
    public ModalOpenReportControllerImpl(OpenReportService service) {
        this.service = service;
    }

    @FXML
    @Override
    public void chooseFile() {
        dto = new OpenFileDto(chooseFileField, dialogStage, fileChooser);
        dir = service.chooseFile(dto);
    }

    @FXML
    @Override
    public void openReport() {
        dto = new OpenFileDto(chooseFileField, dialogStage, fileChooser);
        File report = service.openFile(dir, dto);
        if (report != null) {
            openReport.click(mainApp, report);
        }
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

}
