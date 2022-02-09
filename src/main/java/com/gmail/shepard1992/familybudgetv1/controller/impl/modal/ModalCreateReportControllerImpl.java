package com.gmail.shepard1992.familybudgetv1.controller.impl.modal;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.controller.api.modal.ModalCreateReportController;
import com.gmail.shepard1992.familybudgetv1.controller.buttons.api.ButtonApi;
import com.gmail.shepard1992.familybudgetv1.model.dto.CreateDirectoryDto;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.constants.FilesConstants.*;

@Controller
public class ModalCreateReportControllerImpl implements ModalCreateReportController {

    private MainApplication mainApp;
    private CreateReportService service;
    private MapperUtil mapperUtil;
    private final DirectoryChooser directoryChooser = new DirectoryChooser();
    private File dir;
    private CreateDirectoryDto dto;

    private final ButtonApi createReport = MainApplication::showReportView;

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
        //ToDo нужно как то передать созданный файл в новый отчет
        File report = service.createFile(dir, dto);
        if (report != null) {
            createReport.click(mainApp);
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
