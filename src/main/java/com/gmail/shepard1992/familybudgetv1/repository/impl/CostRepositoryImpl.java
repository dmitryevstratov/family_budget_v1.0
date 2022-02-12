package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.model.Cost;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CostRepositoryImpl implements Repository<Cost> {

    private final FileUtil fileUtil;

    public CostRepositoryImpl(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    public void save(Cost element, File file) {

    }

    @Override
    public void update(Cost element, File file) {

    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        return false;
    }

    @Override
    public List<Cost> getAll(File file) {
        //ToDo получить список моделей из репозитория Reporta
        List<Cost> incomeList = new ArrayList<>();
        /*if (fileUtil.checkEmptyFile(file)) {
            return incomeList;
        }
        try {
            JAXBContext context = JAXBContext.newInstance(IncomeListWrapper.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            IncomeListWrapper listWrapper = (IncomeListWrapper) unmarshaller.unmarshal(file);
            incomeList = listWrapper.getIncome();
        } catch (JAXBException e) {
            e.printStackTrace();
        }*/
        return incomeList;
    }

    @Override
    public void clear(File file) {

    }

    @Override
    public void deleteByCategory(String category, File file) {

    }

}
