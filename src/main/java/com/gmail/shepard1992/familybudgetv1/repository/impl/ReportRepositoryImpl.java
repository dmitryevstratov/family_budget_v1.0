package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.service.model.Report;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ReportRepositoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final FileUtil fileUtil;
    private final ReportRepositoryFacade facade;

    @Autowired
    public ReportRepositoryImpl(FileUtil fileUtil, ReportRepositoryFacade facade) {
        this.fileUtil = fileUtil;
        this.facade = facade;
    }

    @Override
    public boolean save(Report report, File file) {
        return facade.save(report, file);
    }

    @Override
    public Report get(File file) {
        return facade.get(file, fileUtil);
    }

}
