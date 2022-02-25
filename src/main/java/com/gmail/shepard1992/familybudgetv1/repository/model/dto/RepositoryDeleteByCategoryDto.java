package com.gmail.shepard1992.familybudgetv1.repository.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;

import java.io.File;

public class RepositoryDeleteByCategoryDto<E> {

    private final String category;
    private final File file;
    private final Repository<E> repository;

    public RepositoryDeleteByCategoryDto(String category, File file, Repository<E> repository) {
        this.category = category;
        this.file = file;
        this.repository = repository;
    }

    public String getCategory() {
        return category;
    }

    public File getFile() {
        return file;
    }

    public Repository<E> getRepository() {
        return repository;
    }

}
