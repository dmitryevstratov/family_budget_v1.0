package com.gmail.shepard1992.familybudgetv1.service.model.dto;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractDto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CostDto extends AbstractDto {

    private Double sumPlan;

    private Double sumFact;

    private String isBigPurchase;

    private CostDto(CostDtoBuilder costBuilder) {
        super(costBuilder.index, costBuilder.category, costBuilder.type);
        this.sumPlan = costBuilder.sumPlan;
        this.sumFact = costBuilder.sumFact;
        this.isBigPurchase = costBuilder.isBigPurchase;
    }

    public Double getSumPlan() {
        return sumPlan;
    }

    public Double getSumFact() {
        return sumFact;
    }

    public String getIsBigPurchase() {
        return isBigPurchase;
    }

    public StringProperty getCategoryProperty() {
        return new SimpleStringProperty(super.getCategory());
    }

    public StringProperty getTypeProperty() {
        return new SimpleStringProperty(super.getType());
    }

    public StringProperty getSumFactProperty() {
        return new SimpleStringProperty(sumFact.toString());
    }

    public StringProperty getSumPlanProperty() {
        return new SimpleStringProperty(sumPlan.toString());
    }

    public StringProperty getIndexProperty() {
        return new SimpleStringProperty(super.getIndex());
    }

    public StringProperty getIsBigPurchaseProperty() {
        return new SimpleStringProperty(isBigPurchase.toString());
    }

    public void setCostSumPlan(Double sumPlan) {
        this.sumPlan = sumPlan;
    }

    public void setCostSumFact(Double sumFact) {
        this.sumFact = sumFact;
    }

    public void setIsBigPurchase(String isBigPurchase){
        this.isBigPurchase = isBigPurchase;
    }

    public static class CostDtoBuilder {
        private String index;
        private String category;
        private String type;
        private Double sumPlan;
        private Double sumFact;
        private String isBigPurchase;

        public CostDtoBuilder setIndex(String index) {
            this.index = index;
            return this;
        }

        public CostDtoBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public CostDtoBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public CostDtoBuilder setIsBigPurchase(String isBigPurchase) {
            this.isBigPurchase = isBigPurchase;
            return this;
        }

        public CostDtoBuilder setSumPlan(Double sumPlan) {
            this.sumPlan = sumPlan;
            return this;
        }

        public CostDtoBuilder setSumFact(Double sumFact) {
            this.sumFact = sumFact;
            return this;
        }

        public CostDto build() {
            return new CostDto(this);
        }
    }

    @Override
    public String toString() {
        return super.toString() + ", sumPla - " + sumPlan +
                ", sumFact - " + sumFact + ", isBigPurchase - " + isBigPurchase;
    }
}
