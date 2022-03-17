package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.OpenYearReportRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.OpenYearReportService;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFilesDto;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.Arrays;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.service.constants.Logs.SERVICE_LOGS;

@Repository
public class OpenYearReportServiceImpl implements OpenYearReportService {

    private Stage primaryStage;
    private final ValidationUtil validationUtil;
    private final OpenYearReportRepository reportRepository;
    private static final Logger log = Logger.getLogger(OpenYearReportServiceImpl.class.getName());

    @Autowired
    public OpenYearReportServiceImpl(ValidationUtil validationUtil, OpenYearReportRepository reportRepository) {
        this.validationUtil = validationUtil;
        this.reportRepository = reportRepository;
    }

    @Override
    public File[] chooseReportsByYear(ChooseFilesDto dto) {
        return reportRepository.chooseReportsByYear(primaryStage, dto);
    }

    @Override
    public boolean openReportYear(File[] files, Stage stage) {
        if (files != null) {
            log.debug(SERVICE_LOGS + " загрузить годовой отчет по месяцам: " + Arrays.stream(files).map(File::getName).collect(Collectors.joining(", ")));
        }
        return validationUtil.isInputReportYearValid(files, stage);
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
