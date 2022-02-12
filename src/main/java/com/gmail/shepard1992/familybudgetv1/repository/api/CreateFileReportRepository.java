package com.gmail.shepard1992.familybudgetv1.repository.api;

import com.gmail.shepard1992.familybudgetv1.model.dto.CreateDirectoryDto;
import javafx.stage.Stage;

import java.io.File;

public interface CreateFileReportRepository {

    File chooseFile(Stage primaryStage, CreateDirectoryDto dto);

    File createFile(File dir, CreateDirectoryDto dto);

}
