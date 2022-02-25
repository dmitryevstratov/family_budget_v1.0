package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.service.model.api.Dto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IncomeDto implements Dto {
    private final String index;
    private final String category;
    private final String type;
    private Double sum;

    private IncomeDto(IncomeDtoBuilder incomeDtoBuilder) {
        this.index = incomeDtoBuilder.index;
        this.category = incomeDtoBuilder.category;
        this.type = incomeDtoBuilder.type;
        this.sum = incomeDtoBuilder.sum;
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

    public StringProperty getCategoryProperty() {
        return new SimpleStringProperty(category);
    }

    public StringProperty getTypeProperty() {
        return new SimpleStringProperty(type);
    }

    public StringProperty getSumFactProperty() {
        return new SimpleStringProperty(sum.toString());
    }

    public StringProperty getIndexProperty() {
        return new SimpleStringProperty(index);
    }

    public void setIncomeSum(double sum) {
        this.sum = sum;
    }

    public static class IncomeDtoBuilder {
        private String index;
        private String category;
        private String type;
        private Double sum;

        public IncomeDtoBuilder setIndex(String index) {
            this.index = index;
            return this;
        }

        public IncomeDtoBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public IncomeDtoBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public IncomeDtoBuilder setSumFact(Double sum) {
            this.sum = sum;
            return this;
        }

        public IncomeDto build() {
            return new IncomeDto(this);
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
