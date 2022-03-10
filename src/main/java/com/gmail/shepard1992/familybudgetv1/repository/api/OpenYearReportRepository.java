package com.gmail.shepard1992.familybudgetv1.repository.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFilesDto;
import javafx.stage.Stage;

import java.io.File;

public interface OpenYearReportRepository {

    File[] chooseReportsByYear(Stage primaryStage, ChooseFilesDto dto);

}
