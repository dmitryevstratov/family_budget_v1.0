package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TotalServiceByCategoryDto<D, E> {

    private final File file;
    private final Service<D> service;
    private final Repository<E> repository;
    private final Consumer<Map.Entry<String, List<D>>> consumer;

    public TotalServiceByCategoryDto(File file, Service<D> service, Repository<E> repository, Consumer<Map.Entry<String, List<D>>> consumer) {
        this.file = file;
        this.service = service;
        this.repository = repository;
        this.consumer = consumer;
    }

    public File getFile() {
        return file;
    }

    public Service<D> getService() {
        return service;
    }

    public Repository<E> getRepository() {
        return repository;
    }

    public Consumer<Map.Entry<String, List<D>>> getConsumer() {
        return consumer;
    }

}
