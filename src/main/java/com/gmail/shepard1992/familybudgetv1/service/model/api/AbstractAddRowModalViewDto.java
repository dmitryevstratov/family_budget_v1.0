package com.gmail.shepard1992.familybudgetv1.service.model.api;

import java.io.File;

public abstract class AbstractAddRowModalViewDto {

    private final String view;
    private final File file;
    private final AbstractDto dto;

    public AbstractAddRowModalViewDto(String view, File file, AbstractDto dto) {
        this.view = view;
        this.file = file;
        this.dto = dto;
    }

    public String getView() {
        return view;
    }

    public File getFile() {
        return file;
    }

    public AbstractDto getDto() {
        return dto;
    }
}
