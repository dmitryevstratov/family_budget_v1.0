package repository;

import com.gmail.shepard1992.familybudgetv1.config.*;
import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

import static com.gmail.shepard1992.familybudgetv1.constants.FilesConstants.MAIN_DIRECTORY;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ControllerConfig.class,
        ViewConfig.class,
        ServiceConfig.class,
        RepositoryConfig.class,
})
public class RepositoryTest {

    @Autowired
    public Repository<Income> repository;

    private final File file = new File(MAIN_DIRECTORY + "test/");

    @After
    @Before
    public void clearFile() {
        repository.clear(file);
    }

    @Test
    public void test_when_call_save_then_success() {
        Income income = new Income.IncomeBuilder()
                .setIndex("10")
                .setSum(200d)
                .setType("Type")
                .setCategory("Category")
                .build();

        repository.save(income, file);
        List<Income> all = repository.getAll(file);

        assertEquals(1, all.size());
        assertFalse(all.isEmpty());
        assertEquals("10", all.get(0).getIndex());
        assertEquals("Category", all.get(0).getCategory());
        assertEquals("Type", all.get(0).getType());
    }

    @Test
    public void test_when_call_clear_then_file_empty() {
        repository.clear(file);
        List<Income> all = repository.getAll(file);
        assertTrue(all.isEmpty());
    }

    @Test
    public void test_when_call_deleteByCategory_then_success() {
        repository.save(new Income.IncomeBuilder()
                .setIndex("4")
                .setCategory("Dog")
                .setType("Dog")
                .setSum(100d)
                .build(), file);
        repository.save(new Income.IncomeBuilder()
                .setIndex("10")
                .setCategory("Cat")
                .setType("Dog")
                .setSum(100d)
                .build(), file);

        repository.deleteByCategory("Dog", file);
        List<Income> all = repository.getAll(file);

        assertFalse(all.isEmpty());
        assertEquals("10", all.get(0).getIndex());
        assertEquals("Cat", all.get(0).getCategory());
    }

    @Test
    public void test_when_call_deleteByIndex_then_success() {
        repository.save(new Income.IncomeBuilder()
                .setIndex("1")
                .setCategory("Dog")
                .setType("Dog")
                .setSum(100d)
                .build(), file);
        repository.save(new Income.IncomeBuilder()
                .setIndex("3")
                .setCategory("Cat")
                .setType("Dog")
                .setSum(100d)
                .build(), file);

        repository.deleteByIndex(3, file);
        repository.deleteByIndex(23, file);
        List<Income> all = repository.getAll(file);

        assertFalse(all.isEmpty());
        assertEquals("0", all.get(0).getIndex());
        assertEquals("Dog", all.get(0).getCategory());
    }

    @Test
    public void test_when_call_update_then_success() {
        repository.save(new Income.IncomeBuilder()
                .setIndex("0")
                .setCategory("Dog")
                .setType("Dog")
                .setSum(100d)
                .build(), file);

        repository.update(new Income.IncomeBuilder()
                .setIndex("0")
                .setCategory("")
                .setType("")
                .setSum(300d)
                .build(), file);
        List<Income> all = repository.getAll(file);

        assertFalse(all.isEmpty());
        assertEquals("300.0", all.get(0).getSum().toString());
    }

}
