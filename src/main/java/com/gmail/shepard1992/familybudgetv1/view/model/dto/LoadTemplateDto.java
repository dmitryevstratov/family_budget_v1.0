package com.gmail.shepard1992.familybudgetv1.view.model.dto;

import javafx.stage.Stage;

import java.io.File;

public class LoadTemplateDto {

    private final File file;
    private final File tmp;
    private final Stage stage;

    public LoadTemplateDto(File file, File tmp, Stage stage) {
        this.file = file;
        this.tmp = tmp;
        this.stage = stage;
    }

    public File getFile() {
        return file;
    }

    public File getTmp() {
        return tmp;
    }

    public Stage getStage() {
        return stage;
    }

}
