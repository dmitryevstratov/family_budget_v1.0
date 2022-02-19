package com.gmail.shepard1992.familybudgetv1.model.dto.view;

import java.io.File;

public class DeleteRowModalViewDto {

    private final String view;
    private final String index;
    private final File file;

    public DeleteRowModalViewDto(String view, String index, File file) {
        this.view = view;
        this.index = index;
        this.file = file;
    }

    public String getView() {
        return view;
    }

    public String getIndex() {
        return index;
    }

    public File getFile() {
        return file;
    }

}
