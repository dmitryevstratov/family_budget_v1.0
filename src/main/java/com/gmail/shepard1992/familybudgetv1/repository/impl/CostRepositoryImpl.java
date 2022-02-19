package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.model.Cost;
import com.gmail.shepard1992.familybudgetv1.model.CostList;
import com.gmail.shepard1992.familybudgetv1.model.Report;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Repository
public class CostRepositoryImpl implements Repository<Cost> {

    private final ReportRepository reportRepository;

    @Autowired
    public CostRepositoryImpl(ReportRepository reportRepository) {
        this.reportRepository = reportRepository;
    }

    @Override
    public boolean save(Cost element, File file) {
        List<Cost> costList = getAll(file);
        CostList list = new CostList();
        Report report = reportRepository.get(file);
        if (report != null) {
            costList.add(element);
            list.setCost(costList);
            report.setReportCostList(list);
            return reportRepository.save(report, file);
        }
        return false;
    }

    @Override
    public void update(Cost element, File file) {
        List<Cost> costList = getAll(file);
        if (costList.stream().anyMatch(inc -> inc.getIndex().equals(element.getIndex()))) {
            for (Cost inc : costList) {
                if (inc.getIndex().equals(element.getIndex())) {
                    if (!element.getCategory().isEmpty()) inc.setCostCategory(element.getCategory());
                    if (!element.getType().isEmpty()) inc.setCostType(element.getType());
                    if (element.getSumFact() != null) inc.setCostSumFact(element.getSumFact());
                    if (element.getSumPlan() != null) inc.setCostSumPlan(element.getSumPlan());
                }
            }
            clear(file);
            costList.forEach(inc -> save(inc, file));
        }
    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        List<Cost> costList = getAll(file);
        if (costList.stream().anyMatch(inc -> inc.getIndex().equals(index.toString()))) {
            for (int i = 0; i < costList.size(); i++) {
                if (costList.get(i).getIndex().equals(index.toString())) {
                    costList.remove(i);
                    clear(file);
                    for (int j = 0; j < costList.size(); j++) {
                        Cost cost = costList.get(j);
                        if (!cost.getIndex().equals(j + "")) {
                            cost.setCostIndex(j + "");
                            save(cost, file);
                        } else {
                            save(cost, file);
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public List<Cost> getAll(File file) {
        Report report = reportRepository.get(file);
        if (report == null) {
            return new ArrayList<>();
        } else {
            return Objects.requireNonNullElse(report.getCostList().getCost(), new ArrayList<>());
        }
    }

    @Override
    public void clear(File file) {
        Report report = reportRepository.get(file);
        report.setReportCostList(new CostList());
        report.getCostList().setCost(new ArrayList<>());
        reportRepository.save(report, file);
    }

    @Override
    public void deleteByCategory(String category, File file) {
        List<Cost> costList = getAll(file);
        if (costList.stream().anyMatch(inc -> inc.getCategory().equals(category))) {
            for (int i = 0; i < costList.size(); i++) {
                if (costList.get(i).getCategory().equals(category)) {
                    costList.remove(i);
                    clear(file);
                    costList.forEach((cost -> save(cost, file)));
                }
            }
        }
    }

}
