package com.gmail.shepard1992.familybudgetv1.model.dto.view.cost;

import com.gmail.shepard1992.familybudgetv1.model.dto.CostDto;

import java.io.File;

public class AddRowCostModalViewDto {

    private final String view;
    private final CostDto costDto;
    private final File file;

    public AddRowCostModalViewDto(String view, CostDto costDto, File file) {
        this.view = view;
        this.costDto = costDto;
        this.file = file;
    }

    public String getView() {
        return view;
    }

    public CostDto getCostDto() {
        return costDto;
    }

    public File getFile() {
        return file;
    }
}
