package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import javafx.stage.Stage;

import java.io.File;

public class TotalServiceUpdateDto {

    private final TotalService service;
    private final Stage stage;
    private final File file;

    public TotalServiceUpdateDto(TotalService service, Stage stage, File file) {
        this.service = service;
        this.stage = stage;
        this.file = file;
    }

    public TotalService getService() {
        return service;
    }

    public Stage getStage() {
        return stage;
    }

    public File getFile() {
        return file;
    }

}
