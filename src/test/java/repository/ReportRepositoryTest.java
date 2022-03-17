package repository;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.Report;
import com.gmail.shepard1992.familybudgetv1.repository.api.ReportRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.utils.FileConstants.FILE_PATH_TEST;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RepositoryConfig.class,
        UtilConfig.class
})
public class ReportRepositoryTest {

    @Autowired
    public ReportRepository reportRepository;

    private final File file = new File(FILE_PATH_TEST);

    @Test
    public void when_call_get_then_return_report() throws RepositoryException {
        Report report = reportRepository.get(file);

        assertNotNull(report);
    }

    @Test
    public void when_call_save_then_return_true() throws RepositoryException {
        Report report = reportRepository.get(file);
        boolean save = reportRepository.save(report, file);

        assertTrue(save);
    }

}
