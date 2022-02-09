package com.gmail.shepard1992.familybudgetv1.model.dto;

public class ModalViewDto<C> {

    private final String view;
    private final Class<C> classController;
    private final IncomeDto incomeDto;

    public ModalViewDto(String view, Class<C> classController, IncomeDto incomeDto) {
        this.view = view;
        this.classController = classController;
        this.incomeDto = incomeDto;
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

}
