package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.model.dto.CreateDirectoryDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.CreateReportRepository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import javafx.stage.Stage;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class CreateReportRepositoryImpl implements CreateReportRepository {

    private FileUtil fileUtil;

    public CreateReportRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public File chooseFile(Stage primaryStage, CreateDirectoryDto dto) {
        File dir = dto.getDirectoryChooser().showDialog(primaryStage);
        if (dir != null) {
            dto.getText().setText(dir.getAbsolutePath());
            return dir;
        } else {
            dto.getText().setText(null);
        }
        return null;
    }

    @Override
    public File createFile(File dir, CreateDirectoryDto dto) {
        if (dir != null) {
            return fileUtil.getFile(dto, dir);
        }
        return null;
    }

}
