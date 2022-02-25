package com.gmail.shepard1992.familybudgetv1.view.controller.impl.modal;

import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.modal.ModalCreateReportController;
import com.gmail.shepard1992.familybudgetv1.view.controller.buttons.api.ButtonFileApi;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.view.constants.FilesConstants.*;

@Controller
public class ModalCreateReportControllerImpl implements ModalCreateReportController {

    private MainApplication mainApp;
    private CreateReportService service;
    private MapperUtil mapperUtil;
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private File dir;
    private CreateDirectoryDto dto;

    private final ButtonFileApi createReport = MainApplication::showReportView;

    @FXML
    private TextField chooseDirectoryField;

    @FXML
    private ChoiceBox<Integer> year;

    @FXML
    private ChoiceBox<Integer> month;

    public ModalCreateReportControllerImpl() {
    }

    @Autowired
    public ModalCreateReportControllerImpl(CreateReportService service, MapperUtil mapperUtil) {
        this.service = service;
        this.mapperUtil = mapperUtil;
    }

    @FXML
    @Override
    public void chooseFile() {
        dto = new CreateDirectoryDto(directoryChooser, chooseDirectoryField, month, year);
        dir = service.chooseFile(dto);
    }

    @FXML
    @Override
    public void createReport() {
        File report = service.createFile(dir, dto);
        if (report != null) {
            createReport.click(mainApp, report);
        }
    }

    @FXML
    public void initialize() {
        year.setValue(DEFAULT_YEAR);
        month.setValue(DEFAULT_MONTH);
        year.setItems(mapperUtil.getObservableListRange(START_YEAR, END_YEAR));
        month.setItems(mapperUtil.getObservableListRange(START_MONTH, END_MONTH));
    }

    @Override
    public void setMainApp(MainApplication mainApp) {
        this.mainApp = mainApp;
    }
}
