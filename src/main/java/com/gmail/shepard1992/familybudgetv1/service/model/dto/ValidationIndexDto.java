package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import javafx.stage.Stage;

import java.io.File;

public class ValidationIndexDto<E> {

    private final Repository<E> repository;
    private final String index;
    private final File file;
    private final Stage stage;

    public ValidationIndexDto(Repository<E> repository, String index, File file, Stage stage) {
        this.repository = repository;
        this.index = index;
        this.file = file;
        this.stage = stage;
    }

    public Repository<E> getRepository() {
        return repository;
    }

    public String getIndex() {
        return index;
    }

    public File getFile() {
        return file;
    }

    public Stage getStage() {
        return stage;
    }
}
