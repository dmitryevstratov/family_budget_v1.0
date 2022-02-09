package com.gmail.shepard1992.familybudgetv1.controller.api.modal.income;

import com.gmail.shepard1992.familybudgetv1.controller.api.modal.ModalController;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;

public interface ModalNewRowController extends ModalController {

    void setIncome(IncomeDto incomeDto);

}
