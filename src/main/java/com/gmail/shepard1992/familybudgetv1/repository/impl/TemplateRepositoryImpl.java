package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.TemplateRepository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ChooseFileDto;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class TemplateRepositoryImpl implements TemplateRepository {

    private final FileUtil fileUtil;

    @Autowired
    public TemplateRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void save(File file) {
        fileUtil.saveTemplate(file);
    }

    @Override
    public File chooseTemplate(Stage primaryStage, ChooseFileDto dto) {
        return fileUtil.chooseFile(primaryStage, dto);
    }

}
