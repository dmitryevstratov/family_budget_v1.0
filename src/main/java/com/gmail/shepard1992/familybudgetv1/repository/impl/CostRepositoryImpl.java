package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.model.Cost;
import com.gmail.shepard1992.familybudgetv1.model.CostList;
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
public class CostRepositoryImpl implements Repository<Cost> {

    private final ReportRepository reportRepository;
    private final ModelRepositoryUtil facade;

    @Autowired
    public CostRepositoryImpl(ReportRepository reportRepository, ModelRepositoryUtil facade) {
        this.reportRepository = reportRepository;
        this.facade = facade;
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
        Consumer<Cost> consumer = cost -> {
            if (!element.getCategory().isEmpty()) cost.setModelCategory(element.getCategory());
            if (!element.getType().isEmpty()) cost.setModelType(element.getType());
            if (element.getSumFact() != null) cost.setModelSumFact(element.getSumFact());
            if (element.getSumPlan() != null) cost.setModelSumPlan(element.getSumPlan());
        };
        RepositoryUpdateDto<Cost> repositoryUpdateDto = new RepositoryUpdateDto<>(element, file, this, consumer);
        facade.update(repositoryUpdateDto);
    }

    @Override
    public boolean deleteByIndex(Integer index, File file) {
        RepositoryDeleteByIndexDto<Cost> dto = new RepositoryDeleteByIndexDto<>(index, file, this);
        return facade.deleteByIndex(dto);
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
        RepositoryDeleteByCategoryDto<Cost> dto = new RepositoryDeleteByCategoryDto<>(category, file, this);
        facade.deleteByCategory(dto);
    }

}
