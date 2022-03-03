package repository;

import com.gmail.shepard1992.familybudgetv1.repository.api.TemplateRepository;
import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import config.FileUtilConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        FileUtilConfigTest.class,
        RepositoryConfig.class,
        UtilConfig.class
})
public class TemplateRepositoryTest {

    @Autowired
    public TemplateRepository repository;

    @Autowired
    public FileUtil fileUtil;

    @Test
    public void test_when_call_saveTemplate_then_return_success() {
        when(fileUtil.saveTemplate(any())).thenReturn(new File(""));

        repository.save(new File(""));

        verify(fileUtil, times(1)).saveTemplate(any());
    }

}
