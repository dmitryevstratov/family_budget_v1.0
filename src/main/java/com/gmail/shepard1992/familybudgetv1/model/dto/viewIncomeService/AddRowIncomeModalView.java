package com.gmail.shepard1992.familybudgetv1.model.dto.viewIncomeService;

import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;

import java.io.File;

public class AddRowIncomeModalView {

    private String view;
    private IncomeDto incomeDto;
    private File file;

    public AddRowIncomeModalView(String view, IncomeDto incomeDto, File file) {
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
