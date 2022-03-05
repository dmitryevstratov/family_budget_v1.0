package com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal;

import com.gmail.shepard1992.familybudgetv1.service.api.TemplateService;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalLoadTemplateController;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.LoadTemplateDto;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ModalLoadTemplateControllerImpl implements ModalLoadTemplateController {

    private MainApplication mainApp;
    private Stage dialogStage;
    private final FileChooser fileChooser = new FileChooser();
    private File tmp;
    private File file;
    private final TemplateService service;
    private ChooseFileDto dto;

    public ModalLoadTemplateControllerImpl(TemplateService service) {
        this.service = service;
    }

    @FXML
    private TextField chooseFileField;

    @FXML
    @Override
    public void loadTemplate() {
        LoadTemplateDto dto = new LoadTemplateDto(file, tmp, dialogStage);
        if (service.loadTemplate(dto)) {
            dialogStage.close();
        }
    }

    @FXML
    @Override
    public void chooseTemplate() {
        dto = new ChooseFileDto(chooseFileField, dialogStage, fileChooser);
        tmp = service.chooseTemplate(dto);
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
    public void setFile(File file) {
        this.file = file;
    }
}
