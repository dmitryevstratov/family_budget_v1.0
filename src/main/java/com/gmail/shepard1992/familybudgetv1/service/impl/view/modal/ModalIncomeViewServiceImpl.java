package com.gmail.shepard1992.familybudgetv1.service.impl.view.modal;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalDeleteRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalNewRowController;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.income.ModalUpdateRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static javafx.stage.Modality.WINDOW_MODAL;

@Service
public class ModalIncomeViewServiceImpl implements ModalIncomeViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;

    @Autowired
    public ModalIncomeViewServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void showAddRowIncomeModalView(String view, IncomeDto incomeDto) {
        try {
            ModalViewDto<ModalAddRowIncomeController> params = new ModalViewDto<>(view, ModalAddRowIncomeController.class, incomeDto);
            showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showUpdateRowIncomeModalView(String view, IncomeDto incomeDto) {
        try {
            ModalViewDto<ModalUpdateRowIncomeController> params = new ModalViewDto<>(view, ModalUpdateRowIncomeController.class, incomeDto);
            showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showDeleteRowIncomeModalView(String view, String index) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(view));
        loader.setControllerFactory(cls -> context.getBean(ModalDeleteRowIncomeController.class));
        BorderPane rootLayout = null;
        try {
            rootLayout = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ModalDeleteRowIncomeController controller = loader.getController();
        controller.setMainApp(mainApp);

        Stage dialogStage = getStage(rootLayout);
        controller.setDialogStage(dialogStage);
        controller.setIndex(index);

        dialogStage.showAndWait();
    }

    private <C extends ModalNewRowController> void showModalView(ModalViewDto<C> params) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(params.getView()));
        loader.setControllerFactory(cls -> context.getBean(params.getClassController()));
        BorderPane rootLayout = loader.load();

        C controller = loader.getController();
        controller.setMainApp(mainApp);

        Stage dialogStage = getStage(rootLayout);
        controller.setDialogStage(dialogStage);
        controller.setIncome(params.getIncome());

        dialogStage.showAndWait();
    }

    private Stage getStage(BorderPane rootLayout) {
        Stage dialogStage = new Stage();
        dialogStage.initModality(WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(rootLayout);
        dialogStage.setScene(scene);
        return dialogStage;
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

}
