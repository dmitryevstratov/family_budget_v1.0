package com.gmail.shepard1992.familybudgetv1.model.dto;

import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import javafx.scene.control.TableView;

import java.io.File;

public class LoadIncomeDto {

    private final IncomeService incomeService;
    private final TableView<IncomeDto> tableIncome;
    private final File file;

    public LoadIncomeDto(IncomeService incomeService, TableView<IncomeDto> tableIncome, File file) {
        this.incomeService = incomeService;
        this.tableIncome = tableIncome;
        this.file = file;
    }

    public IncomeService getIncomeService() {
        return incomeService;
    }

    public TableView<IncomeDto> getTableIncome() {
        return tableIncome;
    }

    public File getFile() {
        return file;
    }
}
