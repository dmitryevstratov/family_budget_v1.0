package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import javafx.stage.Stage;

import java.io.File;

public interface OpenReportService {

    File chooseFile(ChooseFileDto dto);

    File openFile(File dir, ChooseFileDto dto);

    void setPrimaryStage(Stage primaryStage);

}
