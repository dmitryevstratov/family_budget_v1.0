package service;

import com.gmail.shepard1992.familybudgetv1.repository.api.TemplateRepository;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.service.api.TemplateService;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import config.RepositoryConfigTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        UtilConfig.class,
        RepositoryConfigTest.class
})
public class TemplateControllerServiceTest {

    @Autowired
    public TemplateService service;

    @Autowired
    public TemplateRepository repository;

    @After
    public void resetRepositoryMock() {
        reset(repository);
    }

    @Test
    public void test_when_call_saveTemplate_then_return_success() throws RepositoryException {
        doNothing().when(repository).save(any());

        service.saveTemplate(new File(""));

        verify(repository, times(1)).save(any());
    }

}
