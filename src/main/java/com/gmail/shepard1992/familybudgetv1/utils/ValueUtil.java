package com.gmail.shepard1992.familybudgetv1.utils;

import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.api.Model;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.MonthReportDto;
import javafx.collections.ObservableList;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ValueUtil {

    public String getBigPurchases(List<Cost> costs) {
        if (costs == null) return "";
        return costs.stream()
                .filter(cost -> Boolean.parseBoolean(cost.getBigPurchase()))
                .map(cost -> cost.getType() + " = " + cost.getSumFact() + "\n")
                .collect(Collectors.joining());
    }

    public <M extends Model> Double getSumByModelList(List<M> models) {
        if (models == null) return 0.0;
        return models.stream().mapToDouble(Model::getSumFact).sum();
    }

    public String getTotalPercent(double total, double incomeSum) {
        if (incomeSum == 0.0) return "0.0";
        return new DecimalFormat("#0.00").format(total * 100 / incomeSum);
    }

    public MonthReportDto getTotal(ObservableList<MonthReportDto> list) {
        Double totalIncome = list.stream().map(MonthReportDto::getIncome).reduce(Double::sum).orElse(null);
        Double totalCost = list.stream().map(MonthReportDto::getCost).reduce(Double::sum).orElse(null);
        double totalSum = totalIncome - totalCost;
        return new MonthReportDto.MonthReportDtoBuilder()
                .setMonth("Итого")
                .setIncome(totalIncome)
                .setCost(totalCost)
                .setBigPurchases("")
                .setTotal(totalSum)
                .setTotalPercent(getTotalPercent(totalSum, totalIncome))
                .build();
    }

}
