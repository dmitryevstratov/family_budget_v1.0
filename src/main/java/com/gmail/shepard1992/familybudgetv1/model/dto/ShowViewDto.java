package com.gmail.shepard1992.familybudgetv1.model.dto;

import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

public class ShowViewDto<C> {

    private final String view;
    private final Class<C> classController;
    private final ApplicationContext context;
    private final Stage primaryStage;

    public ShowViewDto(String view, Class<C> classController, ApplicationContext context, Stage primaryStage) {
        this.view = view;
        this.classController = classController;
        this.context = context;
        this.primaryStage = primaryStage;
    }

    public String getView() {
        return view;
    }

    public Class<C> getClassController() {
        return classController;
    }

    public ApplicationContext getContext() {
        return context;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
