package service;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.config.ServiceConfig;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.service.api.Service;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import config.RepositoryConfigTest;
import config.TotalServiceConfigTest;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
public class CostServiceTest {

    @Autowired
    public RepositoryData<Cost> repositoryData;

    @Autowired
    @Qualifier("getCostServiceBean")
    public Service<CostDto> service;

    @Autowired
    @Qualifier("getTotalCostServiceBean")
    public TotalService totalService;

    @After
    public void resetRepositoryMock() {
        reset(repositoryData);
    }

    @Test
    public void test_when_call_getAll_then_return_result() {
        ArrayList<Cost> objects = new ArrayList<>();
        objects.add(new Cost.CostBuilder()
                .setIndex("10")
                .setCategory("dog")
                .setType("type")
                .build());

        Mockito.when(repositoryData.getAll(any())).thenReturn(objects);

        List<CostDto> all = service.getAll(any());
        assertEquals("10", all.get(0).getIndex());
    }

    @Test
    public void test_when_call_setTotalByCategory_then_return_success() {
        List<Cost> costList = new ArrayList<>();
        costList.add(new Cost.CostBuilder()
                .setIndex("1")
                .setType("cwec")
                .setCategory("23e32")
                .setSumFact(100d)
                .setSumPlan(200d)
                .build());
        when(repositoryData.save(any(), any())).thenReturn(true);
        when(repositoryData.getAll(any())).thenReturn(costList);

        totalService.setTotalByCategory(new File(""));

        verify(repositoryData, times(1)).save(any(), any());
    }

    @Test
    public void test_when_call_setTotalAll_then_return_success() {
        List<Cost> costList = new ArrayList<>();
        costList.add(new Cost.CostBuilder()
                .setIndex("1")
                .setType("cwec")
                .setCategory(TOTAL_BY)
                .setSumFact(100d)
                .setSumPlan(200d)
                .build());
        when(repositoryData.save(any(), any())).thenReturn(true);
        when(repositoryData.getAll(any())).thenReturn(costList);

        totalService.setTotalAll(new File(""));

        verify(repositoryData, times(1)).save(any(), any());
    }

}
