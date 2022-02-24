package com.gmail.shepard1992.familybudgetv1.model;

import com.gmail.shepard1992.familybudgetv1.model.api.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"index", "category", "type", "sumFact"})
public class Income implements Model {

    @XmlElement
    private String index;

    @XmlElement
    private String category;

    @XmlElement
    private String type;

    @XmlElement
    private Double sumFact;

    public Income() {
    }

    private Income(IncomeBuilder incomeBuilder) {
        this.index = incomeBuilder.index;
        this.category = incomeBuilder.category;
        this.type = incomeBuilder.type;
        this.sumFact = incomeBuilder.sum;
    }

    @Override
    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public Double getSumFact() {
        return sumFact;
    }

    @Override
    public String getIndex() {
        return index;
    }

    @Override
    public void setModelIndex(String index) {
        this.index = index;
    }

    public void setModelCategory(String category) {
        this.category = category;
    }

    public void setModelType(String type) {
        this.type = type;
    }

    public void setModelSumFact(Double sum) {
        this.sumFact = sum;
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
                ", sum=" + sumFact +
                '}';
    }

}
