package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.OpenFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class OpenFileReportRepositoryImpl implements OpenFileReportRepository {

    private final FileUtil fileUtil;

    @Autowired
    public OpenFileReportRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public File chooseFile(Stage primaryStage, ChooseFileDto dto) {
        return fileUtil.chooseFile(primaryStage, dto);
    }

    @Override
    public File openFile(File dir) {
        return dir;
    }

}
