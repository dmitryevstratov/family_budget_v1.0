package utils;

import com.gmail.shepard1992.familybudgetv1.config.*;
import com.gmail.shepard1992.familybudgetv1.utils.FileUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

import static org.junit.Assert.assertNotNull;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        UtilConfig.class
})
public class FileUtilTest {

    @Autowired
    public FileUtil fileUtil;

    @Test
    public void test_when_call_getFile_then_return_file() {
        /*File file = fileUtil.getFile();

        assertNotNull(file);*/
    }

}
