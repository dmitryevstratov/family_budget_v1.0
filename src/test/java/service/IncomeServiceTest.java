package service;

import com.gmail.shepard1992.familybudgetv1.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import config.RepositoryConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        RepositoryConfig.class,
        RepositoryConfigTest.class,
        UtilConfig.class
})
public class IncomeServiceTest {

    @Autowired
    public Repository<Income> repository;

    @Autowired
    public Service service;

    @Test
    public void test_when_call_getAll_then_return_result() {
        ArrayList<Income> objects = new ArrayList<>();
        objects.add(new Income.IncomeBuilder()
                .setIndex("10")
                .setCategory("dog")
                .setType("type")
                .build());

        Mockito.when(repository.getAll(any())).thenReturn(objects);

        List<IncomeDto> all = service.getAll(any());
        assertEquals("10", all.get(0).getIndex());
    }

}
