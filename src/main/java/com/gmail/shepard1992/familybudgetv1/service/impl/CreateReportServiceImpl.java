package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CreateReportServiceImpl implements CreateReportService {

    private final CreateFileReportRepository repository;
    private Stage primaryStage;

    @Autowired
    public CreateReportServiceImpl(CreateFileReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public File chooseFile(CreateDirectoryDto dto) {
        return repository.chooseFile(primaryStage, dto);
    }

    @Override
    public File createFile(File dir, CreateDirectoryDto dto) {
        return repository.createFile(dir, dto);
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
