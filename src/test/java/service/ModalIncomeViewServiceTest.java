package service;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalIncomeViewService;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.show.DeleteRowModalViewDto;
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
public class ModalIncomeViewServiceTest {

    @Autowired
    public ViewFacade viewFacade;

    @Autowired
    public ModalIncomeViewService modalIncomeViewService;

    @After
    public void resetRepositoryMock() {
        reset(viewFacade);
    }

    @Test
    public void test_when_call_showAddRowIncomeModalView_then_return_result() {
        try {
            doNothing().when(viewFacade).showModalView(any());

            modalIncomeViewService.showAddRowIncomeModalView(new AddRowCostModalViewDto("", new File("path"), new IncomeDto.IncomeDtoBuilder()
                    .setCategory("")
                    .setIndex("")
                    .setType("")
                    .setSumFact(100d)
                    .build()));

            verify(viewFacade, times(1)).showModalView(any());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_when_call_showUpdateRowIncomeModalView_then_return_result() {
        try {
            doNothing().when(viewFacade).showModalView(any());

            modalIncomeViewService.showUpdateRowIncomeModalView(new AddRowCostModalViewDto("", new File("path"), new IncomeDto.IncomeDtoBuilder()
                    .setCategory("")
                    .setIndex("")
                    .setType("")
                    .setSumFact(100d)
                    .build()));

            verify(viewFacade, times(1)).showModalView(any());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_when_call_showDeleteRowIncomeModalView_then_return_result() {
        doNothing().when(viewFacade).showDeleteRowModalView(any());

        modalIncomeViewService.showDeleteRowIncomeModalView(new DeleteRowModalViewDto("", "", new File("")));

        verify(viewFacade, times(1)).showDeleteRowModalView(any());
    }

}
