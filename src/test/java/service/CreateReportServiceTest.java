package service;

import com.gmail.shepard1992.familybudgetv1.repository.api.CreateFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.api.CreateReportService;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.CreateDirectoryDto;
import config.RepositoryConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        RepositoryConfig.class,
        RepositoryConfigTest.class,
        UtilConfig.class
})
public class CreateReportServiceTest {

    @Autowired
    public CreateReportService service;

    @Autowired
    public CreateFileReportRepository repository;

    @Mock
    private CreateDirectoryDto dto;

    @Test
    public void test_when_call_chooseFile_then_return_file() {
        Mockito.when(repository.chooseFile(any(), any())).thenReturn(new File(""));

        File result = service.chooseFile(dto);

        assertNotNull(result);
    }

}
