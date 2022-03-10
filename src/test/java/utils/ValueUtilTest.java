package utils;

import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.CostList;
import com.gmail.shepard1992.familybudgetv1.utils.ValueUtil;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.view.model.dto.MonthReportDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        UtilConfig.class
})
public class ValueUtilTest {

    @Autowired
    public ValueUtil valueUtil;

    @Test
    public void test_getSumByModelList() {
        CostList costList = new CostList();
        List<Cost> costs = new ArrayList<>();
        costs.add(new Cost.CostBuilder()
                .setIndex("1")
                .setType("Type")
                .setCategory("Cat")
                .setSumFact(100d)
                .setSumPlan(200d)
                .build());
        costList.setCost(costs);

        assertEquals(Double.valueOf(100), valueUtil.getSumByModelList(costs));
    }

    @Test
    public void test_getTotalPercent() {
        assertEquals("50,00", valueUtil.getTotalPercent(100, 200));
    }

    @Test
    public void test_getTotal() {
        MonthReportDto dto = new MonthReportDto.MonthReportDtoBuilder()
                .setIncome(1000d)
                .setCost(300d)
                .setMonth("Итого")
                .setMajorPurchases("")
                .setTotal(700d)
                .setTotalPercent("70,00")
                .build();
        ObservableList<MonthReportDto> list = FXCollections.observableArrayList();
        list.add(dto);

        assertTrue(valueUtil.getTotal(list).equals(dto));
    }

}
