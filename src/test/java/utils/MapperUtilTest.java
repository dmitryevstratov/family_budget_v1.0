package utils;

import com.gmail.shepard1992.familybudgetv1.config.UtilConfig;
import com.gmail.shepard1992.familybudgetv1.model.Income;
import com.gmail.shepard1992.familybudgetv1.model.dto.IncomeDto;
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
                .setSum(1000d)
                .build();
        Income income = mapperUtil.convertToIncome(incomeDto);

        assertEquals(incomeDto.getIndex(), income.getIndex());
        assertEquals(incomeDto.getCategory(), income.getCategory());
        assertEquals(incomeDto.getType(), income.getType());
        assertEquals(incomeDto.getSum(), income.getSum());
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
        assertEquals(incomeDto.getSum(), income.getSum());
    }

    @Test
    public void test_mapper_getObservableListRange() {
        ObservableList<Integer> observableListRange = mapperUtil.getObservableListRange(1, 10);

        assertEquals(10, observableListRange.size());
    }

}
