package com.gmail.shepard1992.familybudgetv1.repository.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.repository.model.api.AbstractRepositoryDto;

import java.io.File;

public class RepositoryDeleteByCategoryDto<E> extends AbstractRepositoryDto<E> {

    private final String category;

    public RepositoryDeleteByCategoryDto(File file, Repository<E> repository, String category) {
        super(file, repository);
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

}
