package com.gmail.shepard1992.familybudgetv1.repository.model.api;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;

import java.io.File;

public abstract class AbstractRepositoryDto<E> {

    private final File file;
    private final Repository<E> repository;

    public AbstractRepositoryDto(File file, Repository<E> repository) {
        this.file = file;
        this.repository = repository;
    }

    public File getFile() {
        return file;
    }

    public Repository<E> getRepository() {
        return repository;
    }

}
