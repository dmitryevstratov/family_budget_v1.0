package com.gmail.shepard1992.familybudgetv1.utils.facade;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.ModalDeleteRowController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.ModalNewRowController;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.ModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.show.ShowDeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.view.show.ShowViewDto;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static javafx.stage.Modality.WINDOW_MODAL;

@Component
public class ViewFacade {

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

    public <D, C extends ModalNewRowController<D>> void showModalView(ModalViewDto<C, D> params) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(params.getView()));
        loader.setControllerFactory(cls -> params.getContext().getBean(params.getClassController()));
        BorderPane rootLayout = loader.load();

        C controller = loader.getController();
        controller.setFile(params.getFile());
        controller.setMainApp(params.getMainApp());

        Stage dialogStage = getStage(rootLayout, params.getPrimaryStage());
        controller.setDialogStage(dialogStage);
        controller.setDto(params.getIncome());

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

    public <C extends ModalDeleteRowController> void showDeleteRowModalView(ShowDeleteRowModalViewDto<C> deleteRowModalViewDto) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(deleteRowModalViewDto.getDto().getView()));
        loader.setControllerFactory(cls -> deleteRowModalViewDto.getContext().getBean(deleteRowModalViewDto.getClassController()));
        BorderPane rootLayout = null;
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

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

}
