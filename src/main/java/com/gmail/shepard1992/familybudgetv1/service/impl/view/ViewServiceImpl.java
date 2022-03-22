package com.gmail.shepard1992.familybudgetv1.service.impl.view;

import com.gmail.shepard1992.familybudgetv1.service.impl.view.modal.ModalCostViewServiceImpl;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.YearReportController;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.MainController;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.ReportController;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ShowViewDto;
import com.gmail.shepard1992.familybudgetv1.service.api.ViewService;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@Service
public class ViewServiceImpl implements ViewService {

    private Stage primaryStage;
    private final ApplicationContext context;
    private MainApplication mainApp;
    private final ViewFacade viewFacade;
    private static final Logger log = Logger.getLogger(ViewServiceImpl.class.getName());

    @Autowired
    public ViewServiceImpl(ApplicationContext context, ViewFacade viewFacade) {
        this.context = context;
        this.viewFacade = viewFacade;
    }

    @Override
    public void showRootView(String view) {
        try {
            ShowViewDto<MainController> dto = new ShowViewDto<>(view, MainController.class, context, primaryStage);
            FXMLLoader loader = viewFacade.showView(dto);
            MainController mainController = loader.getController();
            mainController.setMainApp(mainApp);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void showYearReport(String view, File[] files) {
        try {
            ShowViewDto<YearReportController> dto = new ShowViewDto<>(view, YearReportController.class, context, primaryStage);
            FXMLLoader loader = viewFacade.showView(dto);
            YearReportController reportController = loader.getController();
            reportController.setFiles(files);
            reportController.setMainApp(mainApp);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void showReportView(String view, File file) {
        try {
            ShowViewDto<ReportController> dto = new ShowViewDto<>(view, ReportController.class, context, primaryStage);
            FXMLLoader loader = viewFacade.showView(dto);
            ReportController reportController = loader.getController();
            reportController.setFile(file);
            reportController.setMainApp(mainApp);
        } catch (IOException e) {
            log.error(e.getMessage());
            log.error(Arrays.toString(e.getStackTrace()));
        }
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void setDialogStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
}
