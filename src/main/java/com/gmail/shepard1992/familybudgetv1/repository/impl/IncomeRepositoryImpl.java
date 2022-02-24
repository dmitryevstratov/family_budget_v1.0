package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.IncomeList;
import com.gmail.shepard1992.familybudgetv1.model.Report;
import com.gmail.shepard1992.familybudgetv1.model.dto.repository.RepositoryDeleteByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.repository.RepositoryDeleteByIndexDto;
import com.gmail.shepard1992.familybudgetv1.model.dto.repository.RepositoryUpdateDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.utils.ModelRepositoryUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@org.springframework.stereotype.Repository
public class IncomeRepositoryImpl implements Repository<Income> {

    private final ReportRepository reportRepository;
    private final ModelRepositoryUtil facade;

    @Autowired
    public IncomeRepositoryImpl(ReportRepository reportRepository, ModelRepositoryUtil facade) {
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
            return reportRepository.save(report, file);
        }
        return false;
    }

    @Override
    public void update(Income element, File file) {
        Consumer<Income> consumer = income -> {
            if (!element.getCategory().isEmpty()) income.setModelCategory(element.getCategory());
            if (!element.getType().isEmpty()) income.setModelType(element.getType());
            if (element.getSumFact() != null) income.setModelSumFact(element.getSumFact());
        };
        RepositoryUpdateDto<Income> repositoryUpdateDto = new RepositoryUpdateDto<>(element, file, this, consumer);
        facade.update(repositoryUpdateDto);
    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        RepositoryDeleteByIndexDto<Income> dto = new RepositoryDeleteByIndexDto<>(index, file, this);
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
        RepositoryDeleteByCategoryDto<Income> dto = new RepositoryDeleteByCategoryDto<>(category, file, this);
        facade.deleteByCategory(dto);
    }

}
