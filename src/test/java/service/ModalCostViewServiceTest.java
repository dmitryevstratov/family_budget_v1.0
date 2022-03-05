package service;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalCostViewService;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.ModalViewDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.view.cost.AddRowCostModalViewDto;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import config.UtilConfigTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        RepositoryConfig.class,
        UtilConfigTest.class,
        UtilConfig.class
})
public class ModalCostViewServiceTest {

    @Autowired
    public ViewFacade viewFacade;

    @Autowired
    public ModalCostViewService modalCostViewService;

    @After
    public void resetRepositoryMock() {
        reset(viewFacade);
    }

    @Test
    public void test_when_call_showAddRowCostModalView_then_return_result() {
        try {
            doNothing().when(viewFacade).showModalView(any(ModalViewDto.class));

            modalCostViewService.showAddRowCostModalView(new AddRowCostModalViewDto("", new File("path"), new IncomeDto.IncomeDtoBuilder()
                    .setCategory("")
                    .setIndex("")
                    .setType("")
                    .setSumFact(100d)
                    .build()));

            verify(viewFacade, times(1)).showModalView(any(ModalViewDto.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_when_call_showUpdateRowCostModalView_then_return_result() {
        try {
            doNothing().when(viewFacade).showModalView(any(ModalViewDto.class));

            modalCostViewService.showUpdateRowCostModalView(new AddRowCostModalViewDto("", new File("path"), new IncomeDto.IncomeDtoBuilder()
                    .setCategory("")
                    .setIndex("")
                    .setType("")
                    .setSumFact(100d)
                    .build()));

            verify(viewFacade, times(1)).showModalView(any(ModalViewDto.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_when_call_showDeleteRowCostModalView_then_return_result() {
        try {
            doNothing().when(viewFacade).showDeleteRowModalView(any());
        } catch (IOException e) {
            e.printStackTrace();
        }

        modalCostViewService.showDeleteRowCostModalView(new DeleteRowModalViewDto("", "", new File("")));

        try {
            verify(viewFacade, times(1)).showDeleteRowModalView(any());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
