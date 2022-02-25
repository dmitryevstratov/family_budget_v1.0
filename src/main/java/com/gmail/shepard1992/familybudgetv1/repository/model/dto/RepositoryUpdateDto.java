package com.gmail.shepard1992.familybudgetv1.repository.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;

import java.io.File;
import java.util.function.Consumer;

public class RepositoryUpdateDto<E> {

    private final E element;
    private final File file;
    private final Repository<E> repository;
    private final Consumer<E> consumer;

    public RepositoryUpdateDto(E element, File file, Repository<E> repository, Consumer<E> consumer) {
        this.element = element;
        this.file = file;
        this.repository = repository;
        this.consumer = consumer;
    }

    public E getElement() {
        return element;
    }

    public File getFile() {
        return file;
    }

    public Repository<E> getRepository() {
        return repository;
    }

    public Consumer<E> getConsumer() {
        return consumer;
    }

}
