package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CreateReportServiceImpl implements CreateReportService {

    private final CreateFileReportRepository repository;
    private Stage primaryStage;
    private final ValidationUtil validationUtil;

    @Autowired
    public CreateReportServiceImpl(CreateFileReportRepository repository, ValidationUtil validationUtil) {
        this.repository = repository;
        this.validationUtil = validationUtil;
    }

    @Override
    public File chooseDirectory(CreateDirectoryDto dto) {
        return repository.chooseDirectory(primaryStage, dto);
    }

    @Override
    public File createFile(File dir, CreateDirectoryDto dto) {
        if (validationUtil.isInputFilePathValid(dto)) {
            return repository.createFile(dir, dto);
        } else {
            return null;
        }
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
