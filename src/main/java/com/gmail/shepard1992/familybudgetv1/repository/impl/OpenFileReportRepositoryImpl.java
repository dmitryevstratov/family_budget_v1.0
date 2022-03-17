package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.OpenFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.repository.constants.Logs.REPOSITORY_LOGS;

@Repository
public class OpenFileReportRepositoryImpl implements OpenFileReportRepository {

    private final FileUtil fileUtil;
    private static final Logger log = Logger.getLogger(OpenFileReportRepositoryImpl.class.getName());

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
        log.debug(REPOSITORY_LOGS + "открыть в файл " + dir.getName());
        return dir;
    }

}
