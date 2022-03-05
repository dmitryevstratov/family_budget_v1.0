package com.gmail.shepard1992.familybudgetv1.service.model.dto.show;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractModalViewDto;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.File;

public class ShowModalLoadTemplateDto<C> extends AbstractModalViewDto<C> {

    private final String view;
    private final File file;

    public ShowModalLoadTemplateDto(Class<C> classController, ApplicationContext context, MainApplication mainApp, Stage primaryStage, String view, File file) {
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
