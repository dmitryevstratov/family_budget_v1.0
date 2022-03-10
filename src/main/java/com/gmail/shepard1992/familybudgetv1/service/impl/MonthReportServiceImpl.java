package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.MonthReportService;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.LoadMonthReportDto;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.MonthReportDto;
import javafx.scene.control.TableView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class MonthReportServiceImpl implements MonthReportService {

    private final ReportRepository repository;
    private final FileUtil fileUtil;

    @Autowired
    public MonthReportServiceImpl(ReportRepository repository, FileUtil fileUtil) {
        this.repository = repository;
        this.fileUtil = fileUtil;
    }

    @Override
    public void loadMonthReport(File[] files, TableView<MonthReportDto> tableView) {
        LoadMonthReportDto dto = new LoadMonthReportDto(files, tableView, repository);
        fileUtil.loadDtoData(dto);
    }
}
