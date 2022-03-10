package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.MonthReportDto;
import javafx.scene.control.TableView;

import java.io.File;

public class LoadMonthReportDto {

    private final File[] files;
    private final TableView<MonthReportDto> tableView;
    private final ReportRepository repository;

    public LoadMonthReportDto(File[] files, TableView<MonthReportDto> tableView, ReportRepository repository) {
        this.files = files;
        this.tableView = tableView;
        this.repository = repository;
    }

    public File[] getFiles() {
        return files;
    }

    public TableView<MonthReportDto> getTableView() {
        return tableView;
    }

    public ReportRepository getRepository() {
        return repository;
    }

}
