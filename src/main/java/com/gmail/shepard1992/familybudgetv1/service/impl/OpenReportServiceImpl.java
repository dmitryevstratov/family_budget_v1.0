package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.OpenFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.OpenReportService;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.OpenFileDto;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class OpenReportServiceImpl implements OpenReportService {

    private Stage primaryStage;
    private final ValidationUtil validationUtil;
    private final OpenFileReportRepository repository;

    @Autowired
    public OpenReportServiceImpl(ValidationUtil validationUtil, OpenFileReportRepository repository) {
        this.validationUtil = validationUtil;
        this.repository = repository;
    }

    @Override
    public File chooseFile(OpenFileDto dto) {
        return repository.chooseFile(primaryStage, dto);
    }

    @Override
    public File openFile(File dir, OpenFileDto dto) {
        if (validationUtil.isInputFilePathValid(dto)) {
            return repository.openFile(dir);
        } else {
            return null;
        }
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
