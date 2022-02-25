package com.gmail.shepard1992.familybudgetv1.repository.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;

import java.io.File;

public class RepositoryDeleteByIndexDto<E> {

    private final Integer index;
    private final File file;
    private final Repository<E> repository;

    public RepositoryDeleteByIndexDto(Integer index, File file, Repository<E> repository) {
        this.index = index;
        this.file = file;
        this.repository = repository;
    }

    public Integer getIndex() {
        return index;
    }

    public File getFile() {
        return file;
    }

    public Repository<E> getRepository() {
        return repository;
    }

}
