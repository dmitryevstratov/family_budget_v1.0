package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Arrays;

import static com.gmail.shepard1992.familybudgetv1.service.constants.Logs.SERVICE_LOGS;

@Service
public class CreateReportServiceImpl implements CreateReportService {

    private final CreateFileReportRepository repository;
    private Stage primaryStage;
    private final ValidationUtil validationUtil;
    private static final Logger log = Logger.getLogger(CreateReportServiceImpl.class.getName());

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
            log.debug(SERVICE_LOGS + "создать файл с именем " + dir.getName());
            try {
                return repository.createFile(dir, dto);
            } catch (RepositoryException e) {
                log.error(e.getMessage());
                log.error(Arrays.toString(e.getStackTrace()));
                return null;
            }
        } else {
            log.debug(SERVICE_LOGS + "файл не создан ");
            return null;
        }
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
