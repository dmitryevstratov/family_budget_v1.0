package com.gmail.shepard1992.familybudgetv1.model.dto.view;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.File;

public class ModalViewDto<C, D> {

    private final ApplicationContext context;
    private final MainApplication mainApp;
    private final Stage primaryStage;
    private final String view;
    private final Class<C> classController;
    private final D dto;
    private final File file;

    public ModalViewDto(ApplicationContext context, MainApplication mainApp, Stage primaryStage, String view, Class<C> classController, D dto, File file) {
        this.context = context;
        this.mainApp = mainApp;
        this.primaryStage = primaryStage;
        this.view = view;
        this.classController = classController;
        this.dto = dto;
        this.file = file;
    }

    public String getView() {
        return view;
    }

    public Class<C> getClassController() {
        return classController;
    }

    public D getIncome() {
        return dto;
    }

    public File getFile() {
        return file;
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
