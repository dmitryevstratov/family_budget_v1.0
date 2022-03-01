package com.gmail.shepard1992.familybudgetv1.view.model.dto;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.api.AbstractServiceRowDto;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;

public class ServiceDeleteRowDto extends AbstractServiceRowDto {

    public ServiceDeleteRowDto(TextField indexField, Stage dialogStage, File file) {
        super(indexField, dialogStage, file);
    }

}
