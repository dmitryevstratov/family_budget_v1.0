package com.gmail.shepard1992.familybudgetv1.view.model.dto.api;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

public abstract class AbstractFileDto {

    private final TextField text;
    private Stage stage;

    public AbstractFileDto(TextField text, Stage stage) {
        this.text = text;
        this.stage = stage;
    }

    public TextField getText() {
        return text;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
