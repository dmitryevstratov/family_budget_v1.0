package com.gmail.shepard1992.familybudgetv1.repository.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryDeleteByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryDeleteByIndexDto;
import com.gmail.shepard1992.familybudgetv1.repository.model.dto.RepositoryUpdateDto;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.CostList;
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
public class CostRepositoryDataImpl implements RepositoryData<Cost> {

    private final ReportRepository reportRepository;
    private final ModelRepositoryUtil facade;
    private static final Logger log = Logger.getLogger(CostRepositoryDataImpl.class.getName());

    @Autowired
    public CostRepositoryDataImpl(ReportRepository reportRepository, ModelRepositoryUtil facade) {
        this.reportRepository = reportRepository;
        this.facade = facade;
    }

    @Override
    public boolean save(Cost element, File file) throws RepositoryException {
        List<Cost> costList = getAll(file);
        CostList list = new CostList();
        Report report = reportRepository.get(file);
        if (report != null) {
            costList.add(element);
            list.setCost(costList);
            report.setReportCostList(list);
            log.debug(REPOSITORY_LOGS + "сохранить модель в файл " + file.getName());
            return reportRepository.save(report, file);
        }
        log.debug(REPOSITORY_LOGS + "не сохранил модель в файл " + file.getName());
        return false;
    }

    @Override
    public void update(Cost element, File file) throws RepositoryException {
        Consumer<Cost> consumer = cost -> {
            if (!element.getCategory().isEmpty()) cost.setModelCategory(element.getCategory());
            if (!element.getType().isEmpty()) cost.setModelType(element.getType());
            if (element.getSumFact() != null) cost.setModelSumFact(element.getSumFact());
            if (element.getSumPlan() != null) cost.setModelSumPlan(element.getSumPlan());
        };
        RepositoryUpdateDto<Cost> repositoryUpdateDto = new RepositoryUpdateDto<>(file, this, element, consumer);
        facade.update(repositoryUpdateDto);
    }

    @Override
    public boolean deleteByIndex(Integer index, File file) throws RepositoryException {
        RepositoryDeleteByIndexDto<Cost> dto = new RepositoryDeleteByIndexDto<>(file, this, index);
        return facade.deleteByIndex(dto);
    }

    @Override
    public List<Cost> getAll(File file) throws RepositoryException {
        Report report = reportRepository.get(file);
        if (report == null) {
            return new ArrayList<>();
        } else {
            return Objects.requireNonNullElse(report.getCostList().getCost(), new ArrayList<>());
        }
    }

    @Override
    public void clear(File file) throws RepositoryException {
        Report report = reportRepository.get(file);
        report.setReportCostList(new CostList());
        report.getCostList().setCost(new ArrayList<>());
        reportRepository.save(report, file);
    }

    @Override
    public void deleteByCategory(String category, File file) throws RepositoryException {
        RepositoryDeleteByCategoryDto<Cost> dto = new RepositoryDeleteByCategoryDto<>(file, this, category);
        facade.deleteByCategory(dto);
    }

}
