package com.gmail.shepard1992.familybudgetv1.model.dto.view.income;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;

import java.io.File;

public class AddRowIncomeModalViewDto {

    private final String view;
    private final IncomeDto incomeDto;
    private final File file;

    public AddRowIncomeModalViewDto(String view, IncomeDto incomeDto, File file) {
        this.view = view;
        this.incomeDto = incomeDto;
        this.file = file;
    }

    public String getView() {
        return view;
    }

    public IncomeDto getIncomeDto() {
        return incomeDto;
    }

    public File getFile() {
        return file;
    }
}
