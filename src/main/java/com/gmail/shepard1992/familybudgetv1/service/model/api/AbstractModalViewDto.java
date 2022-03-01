package com.gmail.shepard1992.familybudgetv1.service.model.api;

import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

public abstract class AbstractModalViewDto<C> {

    private final Class<C> classController;
    private final ApplicationContext context;
    private final MainApplication mainApp;
    private final Stage primaryStage;

    public AbstractModalViewDto(Class<C> classController, ApplicationContext context, MainApplication mainApp, Stage primaryStage) {
        this.classController = classController;
        this.context = context;
        this.mainApp = mainApp;
        this.primaryStage = primaryStage;
    }

    public Class<C> getClassController() {
        return classController;
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

}
