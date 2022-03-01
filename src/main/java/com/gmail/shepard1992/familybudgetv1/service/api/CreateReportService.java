package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import javafx.stage.Stage;

import java.io.File;

public interface CreateReportService {

    File chooseDirectory(CreateDirectoryDto dto);

    File createFile(File dir, CreateDirectoryDto dto);

    void setPrimaryStage(Stage primaryStage);

}
