package com.gmail.shepard1992.familybudgetv1.model.xmlWrapper;

import com.gmail.shepard1992.familybudgetv1.model.Report;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "reportWrapper")
public class ReportWrapper {

    private Report report;

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}
