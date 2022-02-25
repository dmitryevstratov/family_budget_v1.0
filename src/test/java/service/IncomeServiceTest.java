package service;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.Repository;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import config.RepositoryConfigTest;
import config.TotalServiceConfigTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static com.gmail.shepard1992.familybudgetv1.service.constants.ServiceConstants.TOTAL_BY;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ServiceConfig.class,
        RepositoryConfig.class,
        RepositoryConfigTest.class,
        UtilConfig.class,
        TotalServiceConfigTest.class
})
public class IncomeServiceTest {

    @Autowired
    public Repository<Income> repository;

    @Autowired
    @Qualifier("getIncomeServiceBean")
    public Service<IncomeDto> service;

    @Autowired
    @Qualifier("getTotalIncomeServiceBean")
    public TotalService totalService;

    @After
    public void resetRepositoryMock(){
        reset(repository);
    }

    @Test
    public void test_when_call_getAll_then_return_result() {
        ArrayList<Income> objects = new ArrayList<>();
        objects.add(new Income.IncomeBuilder()
                .setIndex("10")
                .setCategory("dog")
                .setType("type")
                .build());

        when(repository.getAll(any())).thenReturn(objects);

        List<IncomeDto> all = service.getAll(any());
        assertEquals("10", all.get(0).getIndex());
    }

    @Test
    public void test_when_call_setTotalByCategory_then_return_success() {
        List<Income> incomeList = new ArrayList<>();
        incomeList.add(new Income.IncomeBuilder()
                .setIndex("1")
                .setType("cwec")
                .setCategory("23e32")
                .setSum(100d)
                .build());
        when(repository.save(any(), any())).thenReturn(true);
        when(repository.getAll(any())).thenReturn(incomeList);

        totalService.setTotalByCategory(new File(""));

        verify(repository, times(1)).save(any(), any());
    }

    @Test
    public void test_when_call_setTotalAll_then_return_success() {
        List<Income> incomeList = new ArrayList<>();
        incomeList.add(new Income.IncomeBuilder()
                .setIndex("1")
                .setType("cwec")
                .setCategory(TOTAL_BY)
                .setSum(100d)
                .build());
        when(repository.save(any(), any())).thenReturn(true);
        when(repository.getAll(any())).thenReturn(incomeList);

        totalService.setTotalAll(new File(""));

        verify(repository, times(1)).save(any(), any());
    }

}
