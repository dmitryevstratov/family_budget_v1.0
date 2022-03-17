package com.gmail.shepard1992.familybudgetv1.service.impl;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.TotalServiceByCategoryDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.TotalServiceUpdateDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.ValidationIndexDto;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import com.gmail.shepard1992.familybudgetv1.utils.TotalServiceUtil;
import com.gmail.shepard1992.familybudgetv1.utils.ValidationUtil;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceDeleteRowDto;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.ServiceNewRowDto;
import javafx.stage.Stage;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static com.gmail.shepard1992.familybudgetv1.service.constants.Logs.SERVICE_LOGS;
import static com.gmail.shepard1992.familybudgetv1.service.constants.ServiceConstants.*;

@org.springframework.stereotype.Service
public class IncomeServiceImpl implements Service<IncomeDto>, TotalService {

    private final ValidationUtil validationUtil;
    private final RepositoryData<Income> repositoryData;
    private final MapperUtil mapperUtil;
    private final IndexUtil<IncomeDto> indexUtil;
    private final TotalServiceUtil totalServiceUtil;
    private static final Logger log = Logger.getLogger(IncomeServiceImpl.class.getName());

    @Autowired
    public IncomeServiceImpl(ValidationUtil validationUtil, RepositoryData<Income> repositoryData, MapperUtil mapperUtil, IndexUtil<IncomeDto> indexUtil, TotalServiceUtil totalServiceUtil) {
        this.validationUtil = validationUtil;
        this.repositoryData = repositoryData;
        this.mapperUtil = mapperUtil;
        this.indexUtil = indexUtil;
        this.totalServiceUtil = totalServiceUtil;
    }

    @Override
    public boolean addRow(ServiceNewRowDto params) {
        if (validationUtil.isInputAddValid(params)) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setCategory(params.getCategory().getText())
                    .setIndex(indexUtil.incrementIndex(getAll(params.getFile())))
                    .setSumFact(Double.parseDouble(params.getSumFact().getText()))
                    .setType(params.getType().getText()).build();
            repositoryData.save(mapperUtil.convertToIncome(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            log.debug(SERVICE_LOGS + "добавить запись " + dto.toString());
            return true;
        } else {
            log.debug(SERVICE_LOGS + "запись не добавлена");
            return false;
        }
    }

    @Override
    public boolean updateRow(ServiceNewRowDto params) {
        ValidationIndexDto<Income> validationIndexDto = new ValidationIndexDto<>(
                repositoryData,
                params.getIndex().getText(),
                params.getFile(),
                params.getDialogStage()
        );
        if (validationUtil.isInputUpdateValid(params) && validationUtil.isIndexValid(validationIndexDto)) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setIndex(params.getIndex().getText())
                    .setCategory(params.getCategory().getText())
                    .setType(params.getType().getText())
                    .build();
            if (!params.getSumFact().getText().isEmpty()) {
                dto.setIncomeSum(Double.parseDouble(params.getSumFact().getText()));
            }
            repositoryData.update(mapperUtil.convertToIncome(dto), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            log.debug(SERVICE_LOGS + "редактировать запись " + dto.toString());
            return true;
        }
        log.debug(SERVICE_LOGS + "запись не редактирована");
        return false;
    }

    @Override
    public boolean deleteRow(ServiceDeleteRowDto params) {
        ValidationIndexDto<Income> validationIndexDto = new ValidationIndexDto<>(
                repositoryData,
                params.getIndexField().getText(),
                params.getFile(),
                params.getDialogStage()
        );
        if (validationUtil.isInputDeleteValid(params) && validationUtil.isIndexValid(validationIndexDto)) {
            boolean deleteByIndex = repositoryData.deleteByIndex(Integer.parseInt(params.getIndexField().getText()), params.getFile());
            updateTotal(params.getDialogStage(), params.getFile());
            log.debug(SERVICE_LOGS + "удалить запись " + params.getIndexField().getText());
            return deleteByIndex;
        } else {
            log.debug(SERVICE_LOGS + "не удалена запись " + params.getIndexField().getText());
            return false;
        }
    }

    @Override
    public List<IncomeDto> getAll(File file) {
        return repositoryData.getAll(file)
                .stream()
                .map(mapperUtil::convertToIncomeDto)
                .collect(Collectors.toList());
    }

    @Override
    public void setTotalByCategory(File file) {
        Consumer<Map.Entry<String, List<IncomeDto>>> consumer = o -> {
            double sum = o.getValue().stream().mapToDouble(IncomeDto::getSum).sum();
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_BY + o.getKey())
                    .setSumFact(sum)
                    .setType(EMPTY)
                    .build();
            repositoryData.save(mapperUtil.convertToIncome(dto), file);
        };
        TotalServiceByCategoryDto<IncomeDto, Income> dto = new TotalServiceByCategoryDto<>(file, this, repositoryData, consumer);
        totalServiceUtil.setTotalByCategory(dto);
    }

    @Override
    public void setTotalAll(File file) {
        List<IncomeDto> allIncomes = getAll(file);
        allIncomes.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_ALL))
                .forEachOrdered(dto -> repositoryData.deleteByCategory(dto.getCategory(), file));
        double totalAll = allIncomes.stream()
                .filter(dto -> dto.getCategory().contains(TOTAL_BY))
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(IncomeDto::getSum)
                .sum();
        if (totalAll != 0.0) {
            IncomeDto dto = new IncomeDto.IncomeDtoBuilder()
                    .setIndex(EMPTY)
                    .setCategory(TOTAL_ALL)
                    .setSumFact(totalAll)
                    .setType(EMPTY)
                    .build();
            repositoryData.save(mapperUtil.convertToIncome(dto), file);
        }
    }

    @Override
    public void updateTotal(Stage stage, File file) {
        TotalServiceUpdateDto dto = new TotalServiceUpdateDto(this, stage, file);
        totalServiceUtil.updateTotal(dto);
    }

}
