package com.gmail.shepard1992.familybudgetv1.model;

import jakarta.xml.bind.annotation.XmlType;

import javax.xml.bind.annotation.XmlElement;

@XmlType(propOrder = {
        "year",
        "month",
        "incomeList",
        "costList"
})
public class Report {

    @XmlElement
    private Integer year;

    @XmlElement
    private Integer month;

    @XmlElement
    private IncomeList incomeList;

    @XmlElement
    private CostList costList;

    public Report() {
    }

    public IncomeList getIncomeList() {
        return incomeList;
    }

    public void setReportIncomeList(IncomeList incomes) {
        this.incomeList = incomes;
    }

    private Report(ReportBuilder reportBuilder) {
        this.year = reportBuilder.year;
        this.month = reportBuilder.month;
        this.incomeList = reportBuilder.incomeList;
        this.costList = reportBuilder.costs;
    }

    public static class ReportBuilder {
        private Integer year;
        private Integer month;
        private IncomeList incomeList;
        private CostList costs;

        public ReportBuilder setYear(Integer year) {
            this.year = year;
            return this;
        }

        public ReportBuilder setMonth(Integer month) {
            this.month = month;
            return this;
        }

        public ReportBuilder setIncomeList(IncomeList incomeList) {
            this.incomeList = incomeList;
            return this;
        }

        public ReportBuilder setCosts(CostList costs) {
            this.costs = costs;
            return this;
        }

        public Report build() {
            return new Report(this);
        }
    }

    @Override
    public String toString() {
        return "Report{" +
                "year=" + year +
                ", month=" + month +
                ", incomeList=" + incomeList +
                ", costList=" + costList +
                '}';
    }
}
