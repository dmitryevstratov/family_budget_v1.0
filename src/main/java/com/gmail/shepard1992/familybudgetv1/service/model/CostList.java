package com.gmail.shepard1992.familybudgetv1.service.model;

import java.util.List;

public class CostList {

    private List<Cost> costs;

    public CostList() {
    }

    public List<Cost> getCost() {
        return costs;
    }

    public void setCost(List<Cost> income) {
        this.costs = income;
    }
}
