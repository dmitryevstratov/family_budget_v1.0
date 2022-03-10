package com.gmail.shepard1992.familybudgetv1.view.model.dto;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.api.AbstractFileDto;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class ChooseFilesDto extends AbstractFileDto {

    private final DirectoryChooser directoryChooser;

    public ChooseFilesDto(TextField text, Stage stage, DirectoryChooser directoryChooser) {
        super(text, stage);
        this.directoryChooser = directoryChooser;
    }

    public DirectoryChooser getDirectoryChooser() {
        return directoryChooser;
    }

}
