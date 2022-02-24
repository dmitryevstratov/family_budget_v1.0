package com.gmail.shepard1992.familybudgetv1.api.mainApplication.cost;

import java.io.File;

public interface CostActionApi {

    void addCostRow(File file);

    void updateCostRow(File file);

    void deleteCostRow(File file);

}
