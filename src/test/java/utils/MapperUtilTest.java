package utils;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.MapperUtil;
import javafx.collections.ObservableList;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        UtilConfig.class,
        RepositoryConfig.class
})
public class MapperUtilTest {

    @Autowired
    public MapperUtil mapperUtil;

    @Test
    public void test_mapper_to_income() {
        IncomeDto incomeDto = new IncomeDto.IncomeDtoBuilder()
                .setIndex("1")
                .setCategory("Dog")
                .setType("Type")
                .setSumFact(1000d)
                .build();
        Income income = mapperUtil.convertToIncome(incomeDto);

        assertEquals(incomeDto.getIndex(), income.getIndex());
        assertEquals(incomeDto.getCategory(), income.getCategory());
        assertEquals(incomeDto.getType(), income.getType());
        assertEquals(incomeDto.getSum(), income.getSumFact());
    }

    @Test
    public void test_mapper_to_incomeDto() {
        Income income = new Income.IncomeBuilder()
                .setIndex("1")
                .setCategory("Dog")
                .setType("Type")
                .setSum(1000d)
                .build();
        IncomeDto incomeDto = mapperUtil.convertToIncomeDto(income);

        assertEquals(incomeDto.getIndex(), income.getIndex());
        assertEquals(incomeDto.getCategory(), income.getCategory());
        assertEquals(incomeDto.getType(), income.getType());
        assertEquals(incomeDto.getSum(), income.getSumFact());
    }

    @Test
    public void test_mapper_getObservableListRange() {
        ObservableList<Integer> observableListRange = mapperUtil.getObservableListRange(1, 10);

        assertEquals(10, observableListRange.size());
    }

    @Test
    public void test_mapper_to_cost() {
        CostDto costDto = new CostDto.CostDtoBuilder()
                .setIndex("1")
                .setCategory("Dog")
                .setType("Type")
                .setSumFact(1000d)
                .setSumPlan(1000d)
                .build();
        Cost cost = mapperUtil.convertToCost(costDto);

        assertEquals(costDto.getIndex(), cost.getIndex());
        assertEquals(costDto.getCategory(), cost.getCategory());
        assertEquals(costDto.getType(), cost.getType());
        assertEquals(costDto.getSumFact(), cost.getSumFact());
        assertEquals(costDto.getSumPlan(), cost.getSumPlan());
    }

    @Test
    public void test_mapper_to_costDto() {
        Cost cost = new Cost.CostBuilder()
                .setIndex("1")
                .setCategory("Dog")
                .setType("Type")
                .setSumFact(1000d)
                .setSumPlan(1000d)
                .build();
        CostDto costDto = mapperUtil.convertToCostDto(cost);

        assertEquals(costDto.getIndex(), cost.getIndex());
        assertEquals(costDto.getCategory(), cost.getCategory());
        assertEquals(costDto.getType(), cost.getType());
        assertEquals(costDto.getSumFact(), cost.getSumFact());
        assertEquals(costDto.getSumPlan(), cost.getSumPlan());
    }

    @Test
    public void test_mapper_getNameMonthByNumber(){
        assertEquals("Фер", mapperUtil.getNameMonthByNumber(2));
    }

}
