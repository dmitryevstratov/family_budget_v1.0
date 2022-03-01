package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.OpenFileDto;
import javafx.stage.Stage;

import java.io.File;

public interface OpenReportService {

    File chooseFile(OpenFileDto dto);

    File openFile(File dir, OpenFileDto dto);

    void setPrimaryStage(Stage primaryStage);

}
