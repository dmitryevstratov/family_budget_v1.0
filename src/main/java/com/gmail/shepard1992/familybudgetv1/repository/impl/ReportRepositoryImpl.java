package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.model.Report;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ReportRepositoryFacade;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;

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
    public boolean save(Report report, File file) throws RepositoryException {
        try {
            return facade.save(report, file);
        } catch (JAXBException | SAXException exception) {
            throw new RepositoryException("Ошибка сохранения отчета", exception);
        }
    }

    @Override
    public Report get(File file) throws RepositoryException {
        try {
            return facade.get(file, fileUtil);
        } catch (IOException | JAXBException exception) {
            throw new RepositoryException("Ошибка загрузки отчета", exception);
        }
    }

}
