package com.gmail.shepard1992.familybudgetv1.repository.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.model.api.AbstractRepositoryDto;

import java.io.File;

public class RepositoryDeleteByCategoryDto<E> extends AbstractRepositoryDto<E> {

    private final String category;

    public RepositoryDeleteByCategoryDto(File file, RepositoryData<E> repositoryData, String category) {
        super(file, repositoryData);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
