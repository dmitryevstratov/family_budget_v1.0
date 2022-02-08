package com.gmail.shepard1992.familybudgetv1.model.dto;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class ParamsForServiceUpdateRowDto {

    private final TextField index;
    private final TextField category;
    private final TextField type;
    private final TextField sum;
    private final Stage dialogStage;
    private final File file;

    public ParamsForServiceUpdateRowDto(TextField index, TextField category, TextField type, TextField sum, Stage dialogStage, File file) {
        this.index = index;
        this.category = category;
        this.type = type;
        this.sum = sum;
        this.dialogStage = dialogStage;
        this.file = file;

    }

    public TextField getCategory() {
        return category;
    }

    public TextField getType() {
        return type;
    }

    public TextField getSum() {
        return sum;
    }

    public Stage getDialogStage() {
        return dialogStage;
    }

    public File getFile() {
        return file;
    }

    public TextField getIndex() {
        return index;
    }
}
