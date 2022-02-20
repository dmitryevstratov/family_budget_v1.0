package utils;

import com.gmail.shepard1992.familybudgetv1.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static com.gmail.shepard1992.familybudgetv1.constants.FilesConstants.FILE_PATH_TEST;
import static org.junit.Assert.assertFalse;

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
        boolean result = fileUtil.checkEmptyFile(file);

        assertFalse(result);
    }

}
