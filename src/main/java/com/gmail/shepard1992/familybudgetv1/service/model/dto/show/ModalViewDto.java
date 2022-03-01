package com.gmail.shepard1992.familybudgetv1.service.model.dto.show;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractDto;
import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractModalViewDto;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.File;

public class ModalViewDto<C> extends AbstractModalViewDto<C> {

    private final String view;
    private final AbstractDto dto;
    private final File file;

    public ModalViewDto(Class<C> classController, ApplicationContext context, MainApplication mainApp, Stage primaryStage, String view, AbstractDto dto, File file) {
        super(classController, context, mainApp, primaryStage);
        this.view = view;
        this.dto = dto;
        this.file = file;
    }

    public String getView() {
        return view;
    }

    public AbstractDto getDto() {
        return dto;
    }

    public File getFile() {
        return file;
    }

}
