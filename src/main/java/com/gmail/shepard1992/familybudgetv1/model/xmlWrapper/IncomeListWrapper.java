package com.gmail.shepard1992.familybudgetv1.model.xmlWrapper;

import com.gmail.shepard1992.familybudgetv1.model.Income;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "incomes")
public class IncomeListWrapper {

    private List<Income> incomeList;

    public List<Income> getIncome() {
        return incomeList;
    }

    public void setIncome(List<Income> incomeList) {
        this.incomeList = incomeList;
    }
}
