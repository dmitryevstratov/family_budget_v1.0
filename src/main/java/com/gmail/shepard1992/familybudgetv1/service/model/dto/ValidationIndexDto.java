package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import javafx.stage.Stage;

import java.io.File;

public class ValidationIndexDto<E> {

    private final RepositoryData<E> repositoryData;
    private final String index;
    private final File file;
    private final Stage stage;

    public ValidationIndexDto(RepositoryData<E> repositoryData, String index, File file, Stage stage) {
        this.repositoryData = repositoryData;
        this.index = index;
        this.file = file;
        this.stage = stage;
    }

    public RepositoryData<E> getRepositoryData() {
        return repositoryData;
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
