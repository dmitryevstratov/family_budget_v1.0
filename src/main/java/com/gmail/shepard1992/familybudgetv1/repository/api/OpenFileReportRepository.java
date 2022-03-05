package com.gmail.shepard1992.familybudgetv1.repository.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import javafx.stage.Stage;

import java.io.File;

public interface OpenFileReportRepository {

    File chooseFile(Stage primaryStage, ChooseFileDto dto);

    File openFile(File dir);

}
