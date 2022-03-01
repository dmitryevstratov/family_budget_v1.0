package com.gmail.shepard1992.familybudgetv1.repository.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.repository.model.api.AbstractRepositoryDto;

import java.io.File;

public class RepositoryDeleteByIndexDto<E> extends AbstractRepositoryDto<E> {

    private final Integer index;

    public RepositoryDeleteByIndexDto(File file, Repository<E> repository, Integer index) {
        super(file, repository);
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }


}
