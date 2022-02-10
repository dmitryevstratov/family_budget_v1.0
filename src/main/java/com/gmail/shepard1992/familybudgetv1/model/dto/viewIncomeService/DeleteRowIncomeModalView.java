package com.gmail.shepard1992.familybudgetv1.model.dto.viewIncomeService;

import java.io.File;

public class DeleteRowIncomeModalView {

    private String view;
    private String index;
    private File file;

    public DeleteRowIncomeModalView(String view, String index, File file) {
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
