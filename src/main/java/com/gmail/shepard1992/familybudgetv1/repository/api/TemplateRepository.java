package com.gmail.shepard1992.familybudgetv1.repository.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import javafx.stage.Stage;

import java.io.File;

public interface TemplateRepository {

    void save(File file);

    File chooseTemplate(Stage primaryStage, ChooseFileDto dto);

}
