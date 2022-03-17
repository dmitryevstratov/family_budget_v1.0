package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.OpenFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.OpenReportService;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.service.constants.Logs.SERVICE_LOGS;

@Service
public class OpenReportServiceImpl implements OpenReportService {

    private Stage primaryStage;
    private final ValidationUtil validationUtil;
    private final OpenFileReportRepository repository;
    private static final Logger log = Logger.getLogger(OpenReportServiceImpl.class.getName());

    @Autowired
    public OpenReportServiceImpl(ValidationUtil validationUtil, OpenFileReportRepository repository) {
        this.validationUtil = validationUtil;
        this.repository = repository;
    }

    @Override
    public File chooseFile(ChooseFileDto dto) {
        return repository.chooseFile(primaryStage, dto);
    }

    @Override
    public File openFile(File dir, ChooseFileDto dto) {
        if (validationUtil.isInputFilePathValid(dto)) {
            log.debug(SERVICE_LOGS + "открыть отчет с именем " + dir.getName());
            return repository.openFile(dir);
        } else {
            log.debug(SERVICE_LOGS + "отчет не открыт ");
            return null;
        }
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
