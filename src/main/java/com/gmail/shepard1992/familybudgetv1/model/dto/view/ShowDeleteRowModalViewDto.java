package com.gmail.shepard1992.familybudgetv1.model.dto.view;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.utils.ViewUtil;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

public class ShowDeleteRowModalViewDto<C> {

    private final DeleteRowModalViewDto dto;
    private final ApplicationContext context;
    private final MainApplication mainApp;
    private final Class<C> classController;
    private final Stage primaryStage;
    private final ViewUtil viewUtil;

    public ShowDeleteRowModalViewDto(DeleteRowModalViewDto dto, ApplicationContext context, MainApplication mainApp, Class<C> classController, Stage primaryStage, ViewUtil viewUtil) {
        this.dto = dto;
        this.context = context;
        this.mainApp = mainApp;
        this.classController = classController;
        this.primaryStage = primaryStage;
        this.viewUtil = viewUtil;
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

    public ViewUtil getViewUtil() {
        return viewUtil;
    }

    public Class<C> getClassController() {
        return classController;
    }
}
