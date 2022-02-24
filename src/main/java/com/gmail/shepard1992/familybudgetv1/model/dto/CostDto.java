package com.gmail.shepard1992.familybudgetv1.model.dto;

import com.gmail.shepard1992.familybudgetv1.model.api.Dto;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CostDto implements Dto {

    private String index;

    private String category;

    private String type;

    private Double sumPlan;

    private Double sumFact;

    public CostDto() {
    }

    private CostDto(CostDtoBuilder costBuilder) {
        this.index = costBuilder.index;
        this.category = costBuilder.category;
        this.type = costBuilder.type;
        this.sumPlan = costBuilder.sumPlan;
        this.sumFact = costBuilder.sumFact;

    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public Double getSumPlan() {
        return sumPlan;
    }

    public Double getSumFact() {
        return sumFact;
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
        return new SimpleStringProperty(sumFact.toString());
    }

    public StringProperty getSumPlanProperty() {
        return new SimpleStringProperty(sumPlan.toString());
    }

    public StringProperty getIndexProperty() {
        return new SimpleStringProperty(index);
    }

    public void setCostSumPlan(Double sumPlan) {
        this.sumPlan = sumPlan;
    }

    public void setCostSumFact(Double sumFact) {
        this.sumFact = sumFact;
    }

    public static class CostDtoBuilder {
        private String index;
        private String category;
        private String type;
        private Double sumPlan;
        private Double sumFact;

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


}
