package repository;


import com.gmail.shepard1992.familybudgetv1.repository.api.OpenFileReportRepository;
import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RepositoryConfig.class,
        UtilConfig.class
})
public class OpenFileReportRepositoryTest {

    @Autowired
    public OpenFileReportRepository repository;

    @Test
    public void test_when_call_openFile_then_return_file(){
        File file = new File("file");
        File result = repository.openFile(file);

        assertEquals(file, result);
    }

}
