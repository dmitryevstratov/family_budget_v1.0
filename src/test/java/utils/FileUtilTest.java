package utils;

import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static com.gmail.shepard1992.familybudgetv1.utils.FileConstants.FILE_PATH_TEST;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        UtilConfig.class
})
public class FileUtilTest {

    @Autowired
    public FileUtil fileUtil;

    private final File file = new File(FILE_PATH_TEST);

    @Test
    public void test_when_call_checkEmptyFile_then_return_result() {
        assertFalse(fileUtil.checkEmptyFile(file));
    }

    @Test
    public void test_when_call_saveTemplate_then_return_success() {
        assertNotNull(fileUtil.saveTemplate(file));
    }

}
