package com.gmail.shepard1992.familybudgetv1.utils.facade;

import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.*;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalDeleteRowController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalNewRowController;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static javafx.stage.Modality.WINDOW_MODAL;

@Component
public class ViewFacade {

    //ToDo обработка ошибок

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

    public void showModalView(String view, Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(view));
        BorderPane rootLayout = loader.load();

        Stage dialogStage = getStage(rootLayout, stage);

        dialogStage.showAndWait();
    }

    public <C extends ModalNewRowController> void showModalView(ModalViewDto<C> params) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(params.getView()));
        loader.setControllerFactory(cls -> params.getContext().getBean(params.getClassController()));
        BorderPane rootLayout = loader.load();

        Stage dialogStage = getStage(rootLayout, params.getPrimaryStage());

        C controller = loader.getController();
        controller.setFile(params.getFile());
        controller.setMainApp(params.getMainApp());
        controller.setDialogStage(dialogStage);
        controller.setDto(params.getDto());

        dialogStage.showAndWait();
    }

    public <C extends ModalController> void showModalView(ShowModalLoadTemplateDto<C> dto) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(dto.getView()));
        loader.setControllerFactory(cls -> dto.getContext().getBean(dto.getClassController()));
        BorderPane rootLayout = loader.load();

        Stage dialogStage = getStage(rootLayout, dto.getPrimaryStage());

        C controller = loader.getController();
        controller.setFile(dto.getFile());
        controller.setMainApp(dto.getMainApp());
        controller.setDialogStage(dialogStage);

        dialogStage.showAndWait();
    }

    public Stage getStage(BorderPane rootLayout, Stage primaryStage) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(rootLayout);
        dialogStage.setScene(scene);
        return dialogStage;
    }

    public <C extends ModalDeleteRowController> void showDeleteRowModalView(ShowDeleteRowModalViewDto<C> deleteRowModalViewDto) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(deleteRowModalViewDto.getDto().getView()));
        loader.setControllerFactory(cls -> deleteRowModalViewDto.getContext().getBean(deleteRowModalViewDto.getClassController()));
        BorderPane rootLayout = loader.load();

        ModalDeleteRowController controller = loader.getController();
        controller.setFile(deleteRowModalViewDto.getDto().getFile());
        controller.setMainApp(deleteRowModalViewDto.getMainApp());

        Stage dialogStage = deleteRowModalViewDto.getViewUtil().getStage(rootLayout, deleteRowModalViewDto
                .getPrimaryStage());
        controller.setDialogStage(dialogStage);
        controller.setIndex(deleteRowModalViewDto
                .getDto().getIndex());

        dialogStage.showAndWait();
    }

    public <C extends Controller> void showReportModalView(ShowReportModalViewDto<C> modalDto) {
        try {
            ShowViewDto<C> dto = new ShowViewDto<>(modalDto.getView(), modalDto.getClassController(), modalDto.getContext(), modalDto.getPrimaryStage());
            FXMLLoader loader = showView(dto);
            C controller = loader.getController();
            controller.setMainApp(modalDto.getMainApp());
            controller.setDialogStage(dto.getPrimaryStage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
