package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.MainApplication;
import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.xmlWrapper.IncomeListWrapper;
import com.gmail.shepard1992.familybudgetv1.repository.api.IncomeRepository;
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
import java.util.ArrayList;
import java.util.List;

import static com.gmail.shepard1992.familybudgetv1.constants.PathXsd.INCOME_PATH_XSD;
import static javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI;
import static javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT;

@Repository
public class IncomeRepositoryImpl implements IncomeRepository {

    private final FileUtil fileUtil;

    @Autowired
    public IncomeRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void save(Income income, File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(IncomeListWrapper.class);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            URL schemaURL = MainApplication.class.getResource(INCOME_PATH_XSD);
            Schema schema = schemaFactory.newSchema(schemaURL);

            Marshaller m = context.createMarshaller();
            m.setSchema(schema);
            m.setProperty(JAXB_FORMATTED_OUTPUT, true);

            IncomeListWrapper listWrapper = new IncomeListWrapper();
            List<Income> incomeList = getAll(file);
            incomeList.add(income);
            listWrapper.setIncome(incomeList);
            m.marshal(listWrapper, file);

        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Income update(Income income) {
        return null;
    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        List<Income> allIncomes = getAll(file);
        Income removeIncome = null;
        for (int i = 0; i < allIncomes.size(); i++) {
            if (allIncomes.get(i).getIndex().equals(index)) {
                removeIncome = allIncomes.remove(i);
                break;
            }
        }
        if (removeIncome != null) {
            clear(file);
            allIncomes.forEach((income -> save(income, file)));
            return true;
        }
        return false;
    }

    @Override
    public List<Income> getAll(File file) {
        List<Income> incomeList = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(IncomeListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            if (fileUtil.checkEmptyFile(file)) {
                return incomeList;
            } else {
                IncomeListWrapper listWrapper = (IncomeListWrapper) unmarshaller.unmarshal(file);
                incomeList = listWrapper.getIncome();
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return incomeList;
    }

    @Override
    public void clear(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(IncomeListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(JAXB_FORMATTED_OUTPUT, true);

            IncomeListWrapper listWrapper = new IncomeListWrapper();
            List<Income> incomeList = new ArrayList<>();
            listWrapper.setIncome(incomeList);
            m.marshal(listWrapper, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByCategory(String category, File file) {
        List<Income> allIncomes = getAll(file);
        Income removeIncome = null;
        for (int i = 0; i < allIncomes.size(); i++) {
            if (allIncomes.get(i).getCategory().equals(category)) {
                removeIncome = allIncomes.remove(i);
                break;
            }
        }
        if (removeIncome != null) {
            clear(file);
            allIncomes.forEach((income -> save(income, file)));
        }
    }

}
