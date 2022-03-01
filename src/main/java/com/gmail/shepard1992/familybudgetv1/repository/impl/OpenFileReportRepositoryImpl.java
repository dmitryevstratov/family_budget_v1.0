package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.OpenFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.OpenFileDto;
import javafx.stage.Stage;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class OpenFileReportRepositoryImpl implements OpenFileReportRepository {

    @Override
    public File chooseFile(Stage primaryStage, OpenFileDto dto) {
        File dir = dto.getFileChooser().showOpenDialog(primaryStage);
        if (dir != null) {
            dto.getText().setText(dir.getAbsolutePath());
            return dir;
        } else {
            dto.getText().setText(null);
        }
        return null;
    }

    @Override
    public File openFile(File dir) {
        return dir;
    }

}
