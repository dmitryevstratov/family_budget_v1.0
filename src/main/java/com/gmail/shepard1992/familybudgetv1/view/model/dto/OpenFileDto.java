package com.gmail.shepard1992.familybudgetv1.view.model.dto;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.api.AbstractFileDto;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class OpenFileDto extends AbstractFileDto {

    private final FileChooser fileChooser;

    public OpenFileDto(TextField text, Stage stage, FileChooser fileChooser) {
        super(text, stage);
        this.fileChooser = fileChooser;
    }

    public FileChooser getFileChooser() {
        return fileChooser;
    }

}
