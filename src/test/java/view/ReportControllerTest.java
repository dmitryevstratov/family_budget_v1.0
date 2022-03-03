package view;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TemplateService;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.view.controller.api.SaveTemplate;
import com.gmail.shepard1992.familybudgetv1.view.controller.impl.ReportControllerImpl;
import config.ServiceConfigTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceConfigTest.class,
        ServiceConfig.class,
        UtilConfig.class,
        RepositoryConfig.class
})
public class ReportControllerTest {

    @Autowired
    @Qualifier("getTemplateServiceTestBean")
    public TemplateService service;

    @Autowired
    public Service<IncomeDto> incomeService;

    @Autowired
    public Service<CostDto> costService;

    @Autowired
    public FileUtil fileUtil;

    @After
    public void resetServiceMock() {
        reset(service);
    }

    @Test
    public void test_when_call_saveTemplate_then_return_success() {
        SaveTemplate controller = new ReportControllerImpl(
                incomeService,
                costService,
                fileUtil,
                service
        );

        doNothing().when(service).saveTemplate(any());

        controller.saveTemplate();

        verify(service, times(1)).saveTemplate(any());
    }

}
