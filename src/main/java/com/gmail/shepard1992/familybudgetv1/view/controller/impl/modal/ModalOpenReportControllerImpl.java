package com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal;

import com.gmail.shepard1992.familybudgetv1.service.api.OpenReportService;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalOpenReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonApi;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonBackToMainViewApi;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonFileApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

@Controller
public class ModalOpenReportControllerImpl implements ModalOpenReportController, ButtonBackToMainViewApi {

    private MainApplication mainApp;
    private OpenReportService service;
    private Stage dialogStage;
    private final FileChooser fileChooser = new FileChooser();
    private File dir;
    private ChooseFileDto dto;

    private final ButtonFileApi openReport = MainApplication::showReportView;
    private final ButtonApi backToMainView = MainApplication::showRootView;

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
        dto = new ChooseFileDto(chooseFileField, dialogStage, fileChooser);
        dir = service.chooseFile(dto);
    }

    @FXML
    @Override
    public void openReport() {
        dto = new ChooseFileDto(chooseFileField, dialogStage, fileChooser);
        File report = service.openFile(dir, dto);
        if (report != null) {
            openReport.click(mainApp, report);
        }
    }

    @FXML
    @Override
    public void backToMainView() {
        backToMainView.click(mainApp);
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
