package com.gmail.shepard1992.familybudgetv1.model.dto;

import java.io.File;

public class ModalViewDto<C> {

    private final String view;
    private final Class<C> classController;
    private final IncomeDto incomeDto;
    private final File file;

    public ModalViewDto(String view, Class<C> classController, IncomeDto incomeDto, File file) {
        this.view = view;
        this.classController = classController;
        this.incomeDto = incomeDto;
        this.file = file;
    }

    public String getView() {
        return view;
    }

    public Class<C> getClassController() {
        return classController;
    }

    public IncomeDto getIncome() {
        return incomeDto;
    }

    public File getFile() {
        return file;
    }
}
