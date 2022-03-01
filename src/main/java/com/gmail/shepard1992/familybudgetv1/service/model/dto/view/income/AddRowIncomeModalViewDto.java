package com.gmail.shepard1992.familybudgetv1.service.model.dto.view.income;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractAddRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractDto;

import java.io.File;

public class AddRowIncomeModalViewDto extends AbstractAddRowModalViewDto {

    public AddRowIncomeModalViewDto(String view, File file, AbstractDto dto) {
        super(view, file, dto);
    }
}
