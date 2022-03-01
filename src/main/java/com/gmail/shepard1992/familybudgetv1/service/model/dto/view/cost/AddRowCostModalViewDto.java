package com.gmail.shepard1992.familybudgetv1.service.model.dto.view.cost;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractAddRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractDto;

import java.io.File;

public class AddRowCostModalViewDto extends AbstractAddRowModalViewDto {

    public AddRowCostModalViewDto(String view, File file, AbstractDto dto) {
        super(view, file, dto);
    }
}
