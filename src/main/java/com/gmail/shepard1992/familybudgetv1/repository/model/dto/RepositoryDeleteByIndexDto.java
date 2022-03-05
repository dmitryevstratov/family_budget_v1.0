package com.gmail.shepard1992.familybudgetv1.repository.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.model.api.AbstractRepositoryDto;

import java.io.File;

public class RepositoryDeleteByIndexDto<E> extends AbstractRepositoryDto<E> {

    private final Integer index;

    public RepositoryDeleteByIndexDto(File file, RepositoryData<E> repositoryData, Integer index) {
        super(file, repositoryData);
        this.index = index;
    }

    public Integer getIndex() {
        return index;
    }


}
