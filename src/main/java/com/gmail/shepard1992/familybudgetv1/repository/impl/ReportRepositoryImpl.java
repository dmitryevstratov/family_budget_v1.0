package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.model.Report;
import com.gmail.shepard1992.familybudgetv1.model.xmlWrapper.ReportWrapper;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.net.URL;

import static com.gmail.shepard1992.familybudgetv1.constants.PathXsd.REPORT_PATH_XSD;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

@Repository
public class ReportRepositoryImpl implements ReportRepository {

    private final FileUtil fileUtil;

    @Autowired
    public ReportRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void save(Report report, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(ReportWrapper.class);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            URL schemaURL = MainApplication.class.getResource(REPORT_PATH_XSD);
            Schema schema = schemaFactory.newSchema(schemaURL);

            Marshaller m = context.createMarshaller();
            m.setSchema(schema);
            m.setProperty(JAXB_FORMATTED_OUTPUT, true);

            ReportWrapper reportWrapper = new ReportWrapper();
            reportWrapper.setReport(report);
            m.marshal(reportWrapper, file);

        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Report get(File file) {
        Report report = null;
        if (fileUtil.checkEmptyFile(file)) {
            return null;
        }
        try {
            JAXBContext context = JAXBContext.newInstance(ReportWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ReportWrapper reportWrapper = (ReportWrapper) unmarshaller.unmarshal(file);
            report = reportWrapper.getReport();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return report;
    }
}
