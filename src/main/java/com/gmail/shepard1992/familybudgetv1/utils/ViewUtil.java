package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.model.dto.ShowViewDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ViewUtil {

    public <C> FXMLLoader showView(ShowViewDto<C> showViewDto) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(showViewDto.getView()));
        loader.setControllerFactory(cls -> showViewDto.getContext().getBean(showViewDto.getClassController()));
        BorderPane rootLayout = loader.load();

        Scene scene = new Scene(rootLayout);
        showViewDto.getPrimaryStage().setScene(scene);
        showViewDto.getPrimaryStage().show();
        return loader;
    }

}
