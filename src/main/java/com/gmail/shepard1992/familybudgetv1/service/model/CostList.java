package com.gmail.shepard1992.familybudgetv1.service.model;

import java.util.List;

public class CostList {

    private List<Cost> income;

    public CostList() {
    }

    public List<Cost> getCost() {
        return income;
    }

    public void setCost(List<Cost> income) {
        this.income = income;
    }
}
