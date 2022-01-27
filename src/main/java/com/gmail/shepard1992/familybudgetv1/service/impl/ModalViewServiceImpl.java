package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.ModalAddRowIncomeController;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.ParamsForModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;

import java.io.IOException;

import static javafx.stage.Modality.WINDOW_MODAL;

public class ModalViewServiceImpl implements ModalViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;

    public ModalViewServiceImpl(ApplicationContext context) {
        this.context = context;
    }

    @Override
    public void showAddRowIncomeModalView(String view, IncomeDto incomeDto) {
        try {
            ParamsForModalViewDto<ModalAddRowIncomeController> params = new ParamsForModalViewDto<>(view, ModalAddRowIncomeController.class, incomeDto);
            showModalView(params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private <C> void showModalView(ParamsForModalViewDto<C> params) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApplication.class.getResource(params.getView()));
        loader.setControllerFactory(cls -> context.getBean(params.getClassController()));
        BorderPane rootLayout = loader.load();

        ModalAddRowIncomeController controller = loader.getController();
        controller.setMainApp(mainApp);

        Stage dialogStage = new Stage();
        dialogStage.initModality(WINDOW_MODAL);
        dialogStage.initOwner(primaryStage);
        Scene scene = new Scene(rootLayout);
        dialogStage.setScene(scene);
        controller.setDialogStage(dialogStage);
        controller.setIncome(params.getIncome());

        dialogStage.showAndWait();
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
