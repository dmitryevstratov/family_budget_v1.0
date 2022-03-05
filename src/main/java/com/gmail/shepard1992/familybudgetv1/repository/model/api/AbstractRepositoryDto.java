package com.gmail.shepard1992.familybudgetv1.repository.model.api;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;

import java.io.File;

public abstract class AbstractRepositoryDto<E> {

    private final File file;
    private final RepositoryData<E> repositoryData;

    public AbstractRepositoryDto(File file, RepositoryData<E> repositoryData) {
        this.file = file;
        this.repositoryData = repositoryData;
    }

    public File getFile() {
        return file;
    }

    public RepositoryData<E> getRepositoryData() {
        return repositoryData;
    }

}
