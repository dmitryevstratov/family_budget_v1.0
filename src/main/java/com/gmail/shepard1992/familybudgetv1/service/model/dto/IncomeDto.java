package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class IncomeDto extends AbstractDto {

    private Double sum;

    private IncomeDto(IncomeDtoBuilder incomeDtoBuilder) {
        super(incomeDtoBuilder.index, incomeDtoBuilder.category, incomeDtoBuilder.type);
        this.sum = incomeDtoBuilder.sum;
    }

    public Double getSum() {
        return sum;
    }

    public StringProperty getCategoryProperty() {
        return new SimpleStringProperty(super.getCategory());
    }

    public StringProperty getTypeProperty() {
        return new SimpleStringProperty(super.getType());
    }

    public StringProperty getSumFactProperty() {
        return new SimpleStringProperty(sum.toString());
    }

    public StringProperty getIndexProperty() {
        return new SimpleStringProperty(super.getIndex());
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
                "index=" + super.getIndex() +
                ", category='" + super.getCategory() + '\'' +
                ", type='" + super.getType() + '\'' +
                ", sum=" + sum +
                '}';
    }
}
