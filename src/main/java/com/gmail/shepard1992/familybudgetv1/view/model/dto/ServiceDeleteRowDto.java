package com.gmail.shepard1992.familybudgetv1.view.model.dto;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class ServiceDeleteRowDto {

    private final TextField indexField;
    private final Stage dialogStage;
    private final File file;

    public ServiceDeleteRowDto(TextField indexField, Stage dialogStage, File file) {
        this.indexField = indexField;
        this.dialogStage = dialogStage;
        this.file = file;

    }

    public TextField getIndexField() {
        return indexField;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public File getFile() {
        return file;
    }
}
