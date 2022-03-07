package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.OpenYearReportRepository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFilesDto;
import javafx.stage.Stage;

import java.io.File;

public class OpenYearReportRepositoryImpl implements OpenYearReportRepository {

    private final FileUtil fileUtil;

    public OpenYearReportRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public File[] chooseReportsByYear(Stage primaryStage, ChooseFilesDto dto) {
        return fileUtil.chooseFiles(primaryStage, dto);
    }

}
