package com.gmail.shepard1992.familybudgetv1.service.model.dto.show;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractModalViewDto;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

public class ShowDeleteRowModalViewDto<C> extends AbstractModalViewDto<C> {

    private final DeleteRowModalViewDto dto;
    private final ViewFacade viewFacade;

    public ShowDeleteRowModalViewDto(Class<C> classController, ApplicationContext context, MainApplication mainApp, Stage primaryStage, DeleteRowModalViewDto dto, ViewFacade viewFacade) {
        super(classController, context, mainApp, primaryStage);
        this.dto = dto;
        this.viewFacade = viewFacade;
    }

    public DeleteRowModalViewDto getDto() {
        return dto;
    }

    public ViewFacade getViewUtil() {
        return viewFacade;
    }

}
