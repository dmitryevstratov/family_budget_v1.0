package repository;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.repository.exception.RepositoryException;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

import static com.gmail.shepard1992.familybudgetv1.utils.FileConstants.FILE_PATH_TEST;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        RepositoryConfig.class,
        UtilConfig.class
})
public class CostRepositoryDataTest {

    @Autowired
    public RepositoryData<Cost> repositoryData;

    private final File file = new File(FILE_PATH_TEST);

    @After
    @Before
    public void clearFile() throws RepositoryException {
        repositoryData.clear(file);
    }

    @Test
    public void test_when_call_save_then_success() throws RepositoryException {
        Cost cost = new Cost.CostBuilder()
                .setIndex("10")
                .setSumFact(200d)
                .setSumPlan(100d)
                .setType("Type")
                .setCategory("Category")
                .build();

        repositoryData.save(cost, file);
        List<Cost> all = repositoryData.getAll(file);

        assertEquals(1, all.size());
        assertFalse(all.isEmpty());
        assertEquals("10", all.get(0).getIndex());
        assertEquals("Category", all.get(0).getCategory());
        assertEquals("Type", all.get(0).getType());
    }

    @Test
    public void test_when_call_clear_then_file_empty() throws RepositoryException {
        repositoryData.clear(file);
        List<Cost> all = repositoryData.getAll(file);
        assertTrue(all.isEmpty());
    }

    @Test
    public void test_when_call_deleteByCategory_then_success() throws RepositoryException {
        repositoryData.save(new Cost.CostBuilder()
                .setIndex("4")
                .setCategory("Dog")
                .setType("Dog")
                .setSumFact(100d)
                .setSumPlan(100d)
                .build(), file);
        repositoryData.save(new Cost.CostBuilder()
                .setIndex("10")
                .setCategory("Cat")
                .setType("Dog")
                .setSumFact(100d)
                .setSumPlan(100d)
                .build(), file);

        repositoryData.deleteByCategory("Dog", file);
        List<Cost> all = repositoryData.getAll(file);

        assertFalse(all.isEmpty());
        assertEquals("10", all.get(0).getIndex());
        assertEquals("Cat", all.get(0).getCategory());
    }

    @Test
    public void test_when_call_deleteByIndex_then_success() throws RepositoryException {
        repositoryData.save(new Cost.CostBuilder()
                .setIndex("1")
                .setCategory("Dog")
                .setType("Dog")
                .setSumFact(100d)
                .setSumPlan(100d)
                .build(), file);
        repositoryData.save(new Cost.CostBuilder()
                .setIndex("3")
                .setCategory("Cat")
                .setType("Dog")
                .setSumFact(100d)
                .setSumPlan(100d)
                .build(), file);

        repositoryData.deleteByIndex(3, file);
        repositoryData.deleteByIndex(23, file);
        List<Cost> all = repositoryData.getAll(file);

        assertFalse(all.isEmpty());
        assertEquals("0", all.get(0).getIndex());
        assertEquals("Dog", all.get(0).getCategory());
    }

    @Test
    public void test_when_call_update_then_success() throws RepositoryException {
        repositoryData.save(new Cost.CostBuilder()
                .setIndex("0")
                .setCategory("Dog")
                .setType("Dog")
                .setSumPlan(100d)
                .setSumFact(200d)
                .build(), file);

        repositoryData.update(new Cost.CostBuilder()
                .setIndex("0")
                .setCategory("")
                .setType("")
                .setSumPlan(100d)
                .setSumFact(300d)
                .build(), file);
        List<Cost> all = repositoryData.getAll(file);

        assertFalse(all.isEmpty());
        assertEquals("300.0", all.get(0).getSumFact().toString());
    }

    @Test
    public void test_when_call_getAll_then_return_list() throws RepositoryException {
        repositoryData.save(new Cost.CostBuilder()
                .setIndex("1")
                .setCategory("Dog")
                .setType("Dog")
                .setSumPlan(100d)
                .setSumFact(200d)
                .build(), file);
        repositoryData.save(new Cost.CostBuilder()
                .setIndex("2")
                .setCategory("Cat")
                .setType("Dog")
                .setSumPlan(100d)
                .setSumFact(200d)
                .build(), file);
        List<Cost> all = repositoryData.getAll(file);

        assertNotNull(all);
        assertEquals(2, all.size());
    }

}
