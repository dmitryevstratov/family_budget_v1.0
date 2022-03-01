package com.gmail.shepard1992.familybudgetv1.service.model.dto.show;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractModalViewDto;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

public class ShowReportModalViewDto<C> extends AbstractModalViewDto<C> {

    private final String view;

    public ShowReportModalViewDto(Class<C> classController, ApplicationContext context, MainApplication mainApp, Stage primaryStage, String view) {
        super(classController, context, mainApp, primaryStage);
        this.view = view;
    }

    public String getView() {
        return view;
    }


}
