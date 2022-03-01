package com.gmail.shepard1992.familybudgetv1.view.model.dto;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.api.AbstractFileDto;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class CreateDirectoryDto extends AbstractFileDto {

    private final DirectoryChooser directoryChooser;
    private final ChoiceBox<Integer> month;
    private final ChoiceBox<Integer> year;

    public CreateDirectoryDto(TextField text, Stage stage, DirectoryChooser directoryChooser, ChoiceBox<Integer> month, ChoiceBox<Integer> year) {
        super(text, stage);
        this.directoryChooser = directoryChooser;
        this.month = month;
        this.year = year;
    }

    public DirectoryChooser getDirectoryChooser() {
        return directoryChooser;
    }

    public ChoiceBox<Integer> getMonth() {
        return month;
    }

    public ChoiceBox<Integer> getYear() {
        return year;
    }

}
