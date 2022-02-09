package com.gmail.shepard1992.familybudgetv1.model.dto;

import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;

public class CreateDirectoryDto {

    private final DirectoryChooser directoryChooser;
    private final TextField text;
    private final ChoiceBox<Integer> month;
    private final ChoiceBox<Integer> year;

    public CreateDirectoryDto(DirectoryChooser directoryChooser, TextField text, ChoiceBox<Integer> month, ChoiceBox<Integer> year) {
        this.directoryChooser = directoryChooser;
        this.text = text;
        this.month = month;
        this.year = year;
    }

    public DirectoryChooser getDirectoryChooser() {
        return directoryChooser;
    }

    public TextField getText() {
        return text;
    }

    public ChoiceBox<Integer> getMonth() {
        return month;
    }

    public ChoiceBox<Integer> getYear() {
        return year;
    }
}
