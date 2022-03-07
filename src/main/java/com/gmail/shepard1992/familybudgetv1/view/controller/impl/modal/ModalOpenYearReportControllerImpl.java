package com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal;

import com.gmail.shepard1992.familybudgetv1.service.api.OpenYearReportService;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalOpenYearReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonFilesApi;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFilesDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class ModalOpenYearReportControllerImpl implements ModalOpenYearReportController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private final OpenYearReportService service;
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private File[] files;
    private ChooseFilesDto dto;

    private final ButtonFilesApi openYearReport = MainApplication::showYearReportView;

    @FXML
    private TextField chooseFileField;

    @Autowired
    public ModalOpenYearReportControllerImpl(OpenYearReportService service) {
        this.service = service;
    }

    @Override
    public void chooseYear() {
        dto = new ChooseFilesDto(chooseFileField, dialogStage, directoryChooser);
        files = service.chooseReportsByYear(dto);
    }

    @Override
    public void openYearReport() {
        dto = new ChooseFilesDto(chooseFileField, dialogStage, directoryChooser);
        if (service.openReportYear(files, dialogStage)) {
            dialogStage.close();
            openYearReport.click(mainApp, files);
        }
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
