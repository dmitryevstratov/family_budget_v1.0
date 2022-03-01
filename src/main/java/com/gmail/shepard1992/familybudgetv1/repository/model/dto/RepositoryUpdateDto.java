package com.gmail.shepard1992.familybudgetv1.repository.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.repository.model.api.AbstractRepositoryDto;

import java.io.File;
import java.util.function.Consumer;

public class RepositoryUpdateDto<E> extends AbstractRepositoryDto<E> {

    private final E element;
    private final Consumer<E> consumer;

    public RepositoryUpdateDto(File file, Repository<E> repository, E element, Consumer<E> consumer) {
        super(file, repository);
        this.element = element;
        this.consumer = consumer;
    }

    public E getElement() {
        return element;
    }

    public Consumer<E> getConsumer() {
        return consumer;
    }

}
