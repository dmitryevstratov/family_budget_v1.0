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
        List<Income> incomeList = getAll(file);
        try {
            JAXBContext context = JAXBContext.newInstance(IncomeListWrapper.class);

            SchemaFactory schemaFactory = SchemaFactory.newInstance(W3C_XML_SCHEMA_NS_URI);
            URL schemaURL = MainApplication.class.getResource(INCOME_PATH_XSD);
            Schema schema = schemaFactory.newSchema(schemaURL);

            Marshaller m = context.createMarshaller();
            m.setSchema(schema);
            m.setProperty(JAXB_FORMATTED_OUTPUT, true);

            IncomeListWrapper listWrapper = new IncomeListWrapper();
            incomeList.add(income);
            listWrapper.setIncome(incomeList);
            m.marshal(listWrapper, file);

        } catch (JAXBException | SAXException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void update(Income income, File file) {
        List<Income> incomeList = getAll(file);
        if (incomeList.stream()
                .anyMatch(inc -> inc.getIndex().equals(income.getIndex()))) {
            for (Income inc : incomeList) {
                if (inc.getIndex().equals(income.getIndex())) {
                    if (!income.getCategory().isEmpty()) inc.setIncomeCategory(income.getCategory());
                    if (!income.getType().isEmpty()) inc.setIncomeType(income.getType());
                    if (income.getSum() != null) inc.setIncomeSum(income.getSum());
                }
            }
            clear(file);
            incomeList.forEach(inc -> save(inc, file));
        }
    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        List<Income> incomeList = getAll(file);
        if (incomeList.stream()
                .anyMatch(inc -> inc.getIndex().equals(index.toString()))) {
            for (int i = 0; i < incomeList.size(); i++) {
                if (incomeList.get(i).getIndex().equals(index.toString())) {
                    incomeList.remove(i);
                    clear(file);
                    for (int j = 0; j < incomeList.size(); j++) {
                        Income income = incomeList.get(j);
                        if (!income.getIndex().equals(j + "")) {
                            income.setIndex(j + "");
                            save(income, file);
                        } else {
                            save(income, file);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Income> getAll(File file) {
        List<Income> incomeList = new ArrayList<>();
        if (fileUtil.checkEmptyFile(file)) {
            return incomeList;
        }
        try {
            JAXBContext context = JAXBContext.newInstance(IncomeListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            IncomeListWrapper listWrapper = (IncomeListWrapper) unmarshaller.unmarshal(file);
            incomeList = listWrapper.getIncome();
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
        List<Income> incomeList = getAll(file);
        if (incomeList.stream()
                .anyMatch(inc -> inc.getCategory().equals(category))) {
            for (int i = 0; i < incomeList.size(); i++) {
                if (incomeList.get(i).getCategory().equals(category)) {
                    incomeList.remove(i);
                    clear(file);
                    incomeList.forEach((income -> save(income, file)));
                }
            }
        }
    }

}
