package service;

import com.gmail.shepard1992.familybudgetv1.config.*;
import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.IncomeRepository;
import com.gmail.shepard1992.familybudgetv1.service.api.IncomeService;
import config.IncomeRepositoryConfigTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ControllerConfig.class,
        ViewConfig.class,
        ServiceConfig.class,
        MapperConfig.class,
        IndexConfig.class,
        FileConfig.class,
        ValidationConfig.class,
        IncomeRepositoryConfigTest.class
})
public class IncomeServiceTest {

    @Autowired
    public IncomeRepository repository;

    @Autowired
    public IncomeService service;

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
