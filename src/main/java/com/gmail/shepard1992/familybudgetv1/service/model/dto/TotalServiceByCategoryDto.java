package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class TotalServiceByCategoryDto<D, E> {

    private final File file;
    private final Service<D> service;
    private final RepositoryData<E> repositoryData;
    private final Consumer<Map.Entry<String, List<D>>> consumer;

    public TotalServiceByCategoryDto(File file, Service<D> service, RepositoryData<E> repositoryData, Consumer<Map.Entry<String, List<D>>> consumer) {
        this.file = file;
        this.service = service;
        this.repositoryData = repositoryData;
        this.consumer = consumer;
    }

    public File getFile() {
        return file;
    }

    public Service<D> getService() {
        return service;
    }

    public RepositoryData<E> getRepositoryData() {
        return repositoryData;
    }

    public Consumer<Map.Entry<String, List<D>>> getConsumer() {
        return consumer;
    }

}
