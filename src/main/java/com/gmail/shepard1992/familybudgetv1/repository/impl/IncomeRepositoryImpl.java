package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.IncomeList;
import com.gmail.shepard1992.familybudgetv1.model.Report;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Repository
public class IncomeRepositoryImpl implements Repository<Income> {

    private final ReportRepository reportRepository;

    @Autowired
    public IncomeRepositoryImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
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
        List<Income> incomeList = getAll(file);
        if (incomeList.stream().anyMatch(inc -> inc.getIndex().equals(element.getIndex()))) {
            for (Income inc : incomeList) {
                if (inc.getIndex().equals(element.getIndex())) {
                    if (!element.getCategory().isEmpty()) inc.setIncomeCategory(element.getCategory());
                    if (!element.getType().isEmpty()) inc.setIncomeType(element.getType());
                    if (element.getSumFact() != null) inc.setIncomeSum(element.getSumFact());
                }
            }
            clear(file);
            incomeList.forEach(inc -> save(inc, file));
        }
    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        List<Income> incomeList = getAll(file);
        if (incomeList.stream().anyMatch(inc -> inc.getIndex().equals(index.toString()))) {
            for (int i = 0; i < incomeList.size(); i++) {
                if (incomeList.get(i).getIndex().equals(index.toString())) {
                    incomeList.remove(i);
                    clear(file);
                    for (int j = 0; j < incomeList.size(); j++) {
                        Income income = incomeList.get(j);
                        if (!income.getIndex().equals(j + "")) {
                            income.setIncomeIndex(j + "");
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
        List<Income> incomeList = getAll(file);
        if (incomeList.stream().anyMatch(inc -> inc.getCategory().equals(category))) {
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
