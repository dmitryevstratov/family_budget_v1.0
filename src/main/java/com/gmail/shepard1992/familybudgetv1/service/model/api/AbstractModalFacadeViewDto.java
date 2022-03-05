package com.gmail.shepard1992.familybudgetv1.service.model.api;

import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.File;

public abstract class AbstractModalFacadeViewDto<C> extends AbstractModalViewDto<C> {

    private final String view;
    private final File file;

    public AbstractModalFacadeViewDto(Class<C> classController, ApplicationContext context, MainApplication mainApp, Stage primaryStage, String view, File file) {
        super(classController, context, mainApp, primaryStage);
        this.view = view;
        this.file = file;
    }

    public String getView() {
        return view;
    }

    public File getFile() {
        return file;
    }

}
