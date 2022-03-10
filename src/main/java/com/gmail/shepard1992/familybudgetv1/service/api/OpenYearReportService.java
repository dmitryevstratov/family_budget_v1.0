package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFilesDto;
import javafx.stage.Stage;

import java.io.File;

public interface OpenYearReportService {

    File[] chooseReportsByYear(ChooseFilesDto dto);

    boolean openReportYear(File[] files, Stage stage);

    void setPrimaryStage(Stage primaryStage);

}
