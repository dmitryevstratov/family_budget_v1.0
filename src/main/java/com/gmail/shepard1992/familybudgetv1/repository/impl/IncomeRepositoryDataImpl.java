package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryDeleteByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryDeleteByIndexDto;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryUpdateDto;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.IncomeList;
import com.gmail.shepard1992.familybudgetv1.service.model.Report;
import com.gmail.shepard1992.familybudgetv1.utils.ModelRepositoryUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

import static com.gmail.shepard1992.familybudgetv1.repository.constants.Logs.REPOSITORY_LOGS;

@org.springframework.stereotype.Repository
public class IncomeRepositoryDataImpl implements RepositoryData<Income> {

    private final ReportRepository reportRepository;
    private final ModelRepositoryUtil facade;
    private static final Logger log = Logger.getLogger(IncomeRepositoryDataImpl.class.getName());

    @Autowired
    public IncomeRepositoryDataImpl(ReportRepository reportRepository, ModelRepositoryUtil facade) {
        this.reportRepository = reportRepository;
        this.facade = facade;
    }

    @Override
    public boolean save(Income income, File file) {
        List<Income> incomeList = getAll(file);
        IncomeList list = new IncomeList();
        Report report = reportRepository.get(file);
        if (report != null) {
            incomeList.add(income);
            list.setIncome(incomeList);
            report.setReportIncomeList(list);
            log.debug(REPOSITORY_LOGS + "сохранить модель в файл " + file.getName());
            return reportRepository.save(report, file);
        }
        log.debug(REPOSITORY_LOGS + "не сохранил модель в файл " + file.getName());
        return false;
    }

    @Override
    public void update(Income element, File file) {
        Consumer<Income> consumer = income -> {
            if (!element.getCategory().isEmpty()) income.setModelCategory(element.getCategory());
            if (!element.getType().isEmpty()) income.setModelType(element.getType());
            if (element.getSumFact() != null) income.setModelSumFact(element.getSumFact());
        };
        RepositoryUpdateDto<Income> repositoryUpdateDto = new RepositoryUpdateDto<>(file, this, element, consumer);
        facade.update(repositoryUpdateDto);
    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        RepositoryDeleteByIndexDto<Income> dto = new RepositoryDeleteByIndexDto<>(file, this, index);
        return facade.deleteByIndex(dto);
    }

    @Override
    public List<Income> getAll(File file) {
        Report report = reportRepository.get(file);
        if (report == null) {
            return new ArrayList<>();
        } else {
            return Objects.requireNonNullElse(report.getIncomeList().getIncome(), new ArrayList<>());
        }
    }

    @Override
    public void clear(File file) {
        Report report = reportRepository.get(file);
        report.setReportIncomeList(new IncomeList());
        report.getIncomeList().setIncome(new ArrayList<>());
        reportRepository.save(report, file);
    }

    @Override
    public void deleteByCategory(String category, File file) {
        RepositoryDeleteByCategoryDto<Income> dto = new RepositoryDeleteByCategoryDto<>(file, this, category);
        facade.deleteByCategory(dto);
    }

}
