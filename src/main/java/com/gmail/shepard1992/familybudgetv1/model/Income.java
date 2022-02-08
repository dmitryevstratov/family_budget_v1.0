package com.gmail.shepard1992.familybudgetv1.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Objects;

@XmlType(propOrder = {"index", "category", "type", "sum"})
public class Income {

    private String index;

    @XmlElement
    private String category;

    @XmlElement
    private String type;

    @XmlElement
    private Double sum;

    public Income() {
    }

    private Income(IncomeBuilder incomeBuilder) {
        this.index = incomeBuilder.index;
        this.category = incomeBuilder.category;
        this.type = incomeBuilder.type;
        this.sum = incomeBuilder.sum;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public Double getSum() {
        return sum;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public void setIncomeCategory(String category) {
        this.category = category;
    }

    public void setIncomeType(String type) {
        this.type = type;
    }

    public void setIncomeSum(Double sum) {
        this.sum = sum;
    }

    public static class IncomeBuilder {
        private String index;
        private String category;
        private String type;
        private Double sum;

        public IncomeBuilder setIndex(String index) {
            this.index = index;
            return this;
        }

        public IncomeBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public IncomeBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public IncomeBuilder setSum(Double sum) {
            this.sum = sum;
            return this;
        }

        public Income build() {
            return new Income(this);
        }
    }

    @Override
    public String toString() {
        return "Income{" +
                "index=" + index +
                ", category='" + category + '\'' +
                ", type='" + type + '\'' +
                ", sum=" + sum +
                '}';
    }

}
