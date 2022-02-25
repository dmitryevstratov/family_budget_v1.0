package com.gmail.shepard1992.familybudgetv1.service.model.dto.show;

import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

public class ShowDeleteRowModalViewDto<C> {

    private final DeleteRowModalViewDto dto;
    private final ApplicationContext context;
    private final MainApplication mainApp;
    private final Class<C> classController;
    private final Stage primaryStage;
    private final ViewFacade viewFacade;

    public ShowDeleteRowModalViewDto(DeleteRowModalViewDto dto, ApplicationContext context, MainApplication mainApp, Class<C> classController, Stage primaryStage, ViewFacade viewFacade) {
        this.dto = dto;
        this.context = context;
        this.mainApp = mainApp;
        this.classController = classController;
        this.primaryStage = primaryStage;
        this.viewFacade = viewFacade;
    }

    public DeleteRowModalViewDto getDto() {
        return dto;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public MainApplication getMainApp() {
        return mainApp;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public ViewFacade getViewUtil() {
        return viewFacade;
    }

    public Class<C> getClassController() {
        return classController;
    }
}
