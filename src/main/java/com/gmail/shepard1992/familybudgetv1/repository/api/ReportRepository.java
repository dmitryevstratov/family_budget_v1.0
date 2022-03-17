package com.gmail.shepard1992.familybudgetv1.repository.api;

import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.model.Report;

import java.io.File;

public interface ReportRepository {

    boolean save(Report report, File file) throws RepositoryException;

    Report get(File file) throws RepositoryException;

}
