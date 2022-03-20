package com.gmail.shepard1992.familybudgetv1.service.model;

import com.gmail.shepard1992.familybudgetv1.service.model.api.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"index", "category", "type", "sumPlan", "sumFact", "isBigPurchase"})
public class Cost implements Model {

    @XmlElement
    private String index;

    @XmlElement
    private String category;

    @XmlElement
    private String type;

    @XmlElement
    private Double sumPlan;

    @XmlElement
    private Double sumFact;

    @XmlElement
    private String isBigPurchase;

    public Cost() {
    }

    private Cost(CostBuilder costBuilder) {
        this.index = costBuilder.index;
        this.category = costBuilder.category;
        this.type = costBuilder.type;
        this.sumPlan = costBuilder.sumPlan;
        this.sumFact = costBuilder.sumFact;
        this.isBigPurchase = costBuilder.isBigPurchase;
    }

    @Override
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

    public String getBigPurchase() {
        return isBigPurchase;
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

    public void setModelSumPlan(Double sumPlan) {
        this.sumPlan = sumPlan;
    }

    public void setModelSumFact(Double sumFact) {
        this.sumFact = sumFact;
    }

    public void setModelIsBigPurchase(String isBigPurchase){
        this.isBigPurchase = isBigPurchase;
    }

    public static class CostBuilder {
        private String index;
        private String category;
        private String type;
        private Double sumPlan;
        private Double sumFact;
        private String isBigPurchase;

        public CostBuilder setIndex(String index) {
            this.index = index;
            return this;
        }

        public CostBuilder setCategory(String category) {
            this.category = category;
            return this;
        }

        public CostBuilder setType(String type) {
            this.type = type;
            return this;
        }

        public CostBuilder setIsBigPurchase(String isBigPurchase){
            this.isBigPurchase = isBigPurchase;
            return this;
        }

        public CostBuilder setSumPlan(Double sumPlan) {
            this.sumPlan = sumPlan;
            return this;
        }

        public CostBuilder setSumFact(Double sumFact) {
            this.sumFact = sumFact;
            return this;
        }

        public Cost build() {
            return new Cost(this);
        }
    }

}
