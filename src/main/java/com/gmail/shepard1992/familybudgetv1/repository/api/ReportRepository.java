package com.gmail.shepard1992.familybudgetv1.repository.api;

import com.gmail.shepard1992.familybudgetv1.model.Report;

import java.io.File;

public interface ReportRepository {

    void save(Report report, File file);

    Report get(File file);

}
