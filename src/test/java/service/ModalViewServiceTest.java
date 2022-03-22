package service;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.api.ModalViewService;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.utils.exception.UtilException;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.utils.facade.ViewFacade;
import config.UtilConfigTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        RepositoryConfig.class,
        UtilConfigTest.class,
        UtilConfig.class
})
public class ModalViewServiceTest {

    @Autowired
    public ViewFacade viewFacade;

    @Autowired
    public ModalViewService modalViewService;

    @After
    public void resetRepositoryMock() {
        reset(viewFacade);
    }

    @Test
    public void test_when_call_showCreateReportModalView_then_return_result() throws UtilException {
        doNothing().when(viewFacade).showReportModalView(any());

        modalViewService.showCreateReportModalView("");

        verify(viewFacade, times(1)).showReportModalView(any());
    }

    @Test
    public void test_when_call_showOpenReportModalView_then_return_result() throws UtilException {
        doNothing().when(viewFacade).showReportModalView(any());

        modalViewService.showOpenReportModalView("");

        verify(viewFacade, times(1)).showReportModalView(any());
    }

}
