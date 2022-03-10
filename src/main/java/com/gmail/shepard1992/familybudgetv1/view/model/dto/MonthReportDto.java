package com.gmail.shepard1992.familybudgetv1.view.model.dto;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.Objects;

public class MonthReportDto {

    private String month;

    private Double income;

    private Double cost;

    private final Double total;

    private final String totalPercent;

    private final String majorPurchases;

    public MonthReportDto(MonthReportDtoBuilder builder) {
        this.month = builder.month;
        this.income = builder.income;
        this.cost = builder.cost;
        this.total = builder.total;
        this.totalPercent = builder.totalPercent;
        this.majorPurchases = builder.majorPurchases;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public StringProperty getMonthProperty() {
        return new SimpleStringProperty(month);
    }

    public StringProperty getIncomeProperty() {
        return new SimpleStringProperty(income.toString());
    }

    public StringProperty getCostProperty() {
        return new SimpleStringProperty(cost.toString());
    }

    public StringProperty getTotalProperty() {
        return new SimpleStringProperty(total.toString());
    }

    public StringProperty getTotalPercentProperty() {
        return new SimpleStringProperty(totalPercent);
    }

    public StringProperty getMajorPurchasesProperty() {
        return new SimpleStringProperty(majorPurchases);
    }

    public static class MonthReportDtoBuilder {

        private String month;

        private Double income;

        private Double cost;

        private Double total;

        private String totalPercent;

        private String majorPurchases;

        public MonthReportDtoBuilder setMonth(String month) {
            this.month = month;
            return this;
        }

        public MonthReportDtoBuilder setIncome(Double income) {
            this.income = income;
            return this;
        }

        public MonthReportDtoBuilder setCost(Double cost) {
            this.cost = cost;
            return this;
        }

        public MonthReportDtoBuilder setTotal(Double total) {
            this.total = total;
            return this;
        }

        public MonthReportDtoBuilder setTotalPercent(String totalPercent) {
            this.totalPercent = totalPercent;
            return this;
        }

        public MonthReportDtoBuilder setMajorPurchases(String majorPurchases) {
            this.majorPurchases = majorPurchases;
            return this;
        }

        public MonthReportDto build() {
            return new MonthReportDto(this);
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthReportDto dto = (MonthReportDto) o;
        return Objects.equals(month, dto.month) &&
                Objects.equals(income, dto.income) &&
                Objects.equals(cost, dto.cost) &&
                Objects.equals(total, dto.total) &&
                Objects.equals(totalPercent, dto.totalPercent) &&
                Objects.equals(majorPurchases, dto.majorPurchases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(month, income, cost, total, totalPercent, majorPurchases);
    }
}
