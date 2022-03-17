package com.gmail.shepard1992.familybudgetv1.utils.facade;

import com.gmail.shepard1992.familybudgetv1.service.model.Report;
import com.gmail.shepard1992.familybudgetv1.service.model.xmlWrapper.ReportWrapper;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.view.mainApp.MainApplication;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import static com.gmail.shepard1992.familybudgetv1.repository.constants.Logs.REPOSITORY_LOGS;
import static com.gmail.shepard1992.familybudgetv1.repository.constants.PathXsd.REPORT_PATH_XSD;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

@Component
public class ReportRepositoryFacade {

    private static final Logger log = Logger.getLogger(ReportRepositoryFacade.class.getName());

    public boolean save(Report report, File file) throws JAXBException, SAXException {
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
        log.debug(REPOSITORY_LOGS + "отчет записан в файл " + file.getName());
        return true;
    }

    public Report get(File file, FileUtil fileUtil) throws IOException, JAXBException {
        Report report = null;
        if (fileUtil.checkEmptyFile(file)) {
            return null;
        }

        JAXBContext context = JAXBContext.newInstance(ReportWrapper.class);

        Unmarshaller unmarshaller = context.createUnmarshaller();

        ReportWrapper reportWrapper = (ReportWrapper) unmarshaller.unmarshal(file);
        report = reportWrapper.getReport();
        log.debug(REPOSITORY_LOGS + "отчет загружен в файл " + file.getName());
        return report;
    }

}
