package com.gmail.shepard1992.familybudgetv1.service.api;

import com.gmail.shepard1992.familybudgetv1.service.model.api.AbstractAddRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.Controller;

public interface ModalCostViewService extends Controller {

    void showAddRowCostModalView(AbstractAddRowModalViewDto dto);

    void showUpdateRowCostModalView(AbstractAddRowModalViewDto dto);

    void showDeleteRowCostModalView(DeleteRowModalViewDto dto);

}
