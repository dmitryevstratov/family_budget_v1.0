package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.service.model.CostList;
import com.gmail.shepard1992.familybudgetv1.service.model.IncomeList;
import com.gmail.shepard1992.familybudgetv1.service.model.Report;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.repository.constants.Logs.REPOSITORY_LOGS;

@Repository
public class CreateFileReportRepositoryImpl implements CreateFileReportRepository {

    private final FileUtil fileUtil;
    private final ReportRepository reportRepository;
    private static final Logger log = Logger.getLogger(CreateFileReportRepositoryImpl.class.getName());

    @Autowired
    public CreateFileReportRepositoryImpl(FileUtil fileUtil, ReportRepository reportRepository) {
        this.fileUtil = fileUtil;
        this.reportRepository = reportRepository;
    }

    @Override
    public File chooseDirectory(Stage primaryStage, CreateDirectoryDto dto) {
        return fileUtil.chooseDirectory(primaryStage, dto);
    }

    @Override
    public File createFile(File dir, CreateDirectoryDto dto) {
        if (dir != null) {
            File file = fileUtil.getFile(dto, dir);
            if (fileUtil.checkEmptyFile(file)) {
                Report report = new Report.ReportBuilder()
                        .setYear(dto.getYear().getValue())
                        .setMonth(dto.getMonth().getValue())
                        .setIncomeList(new IncomeList())
                        .setCosts(new CostList())
                        .build();
                reportRepository.save(report, file);
            }
            log.debug(REPOSITORY_LOGS + "создать файл " + file.getName());
            return file;
        }
        log.debug(REPOSITORY_LOGS + "файл не создан");
        return null;
    }

}
