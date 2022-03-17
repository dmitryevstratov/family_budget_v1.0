package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.service.model.api.Model;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceDeleteRowDto;

import java.util.function.Consumer;

public class DeleteRowDto<M extends Model> {

    private final ServiceDeleteRowDto params;
    private final ValidationIndexDto<M> validationIndexDto;
    private final Consumer<TotalService> consumer;
    private final TotalService totalService;

    public DeleteRowDto(ServiceDeleteRowDto params, ValidationIndexDto<M> validationIndexDto, Consumer<TotalService> consumer, TotalService totalService) {
        this.params = params;
        this.validationIndexDto = validationIndexDto;
        this.consumer = consumer;
        this.totalService = totalService;
    }

    public ServiceDeleteRowDto getParams() {
        return params;
    }

    public ValidationIndexDto<M> getValidationIndexDto() {
        return validationIndexDto;
    }

    public Consumer<TotalService> getConsumer() {
        return consumer;
    }

    public TotalService getTotalService() {
        return totalService;
    }

}
