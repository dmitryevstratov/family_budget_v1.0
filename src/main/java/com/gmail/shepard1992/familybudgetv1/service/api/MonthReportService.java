package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.view.model.dto.MonthReportDto;
import javafx.scene.control.TableView;

import java.io.File;

public interface MonthReportService {

    void loadMonthReport(File[] files, TableView<MonthReportDto> tableView);

}
