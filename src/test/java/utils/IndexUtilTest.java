package utils;

import com.gmail.shepard1992.familybudgetv1.repository.config.RepositoryConfig;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.IndexUtil;
import com.gmail.shepard1992.familybudgetv1.utils.config.UtilConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        UtilConfig.class,
        RepositoryConfig.class
})
public class IndexUtilTest {

    @Autowired
    public IndexUtil<IncomeDto> indexUtil;

    @Test
    public void test_when_call_incrementIndex_then_return_index() {
        List<IncomeDto> incomeDtoList = new ArrayList<>();
        incomeDtoList.add(new IncomeDto.IncomeDtoBuilder()
                .setIndex("0")
                .setCategory("Cat")
                .setType("Type")
                .setSumFact(100d)
                .build());
        String incrementIndex = indexUtil.incrementIndex(incomeDtoList);
        assertEquals("1", incrementIndex);
    }

    @Test
    public void test_when_call_incrementIndex_then_return_zero() {
        List<IncomeDto> incomeDtoList = new ArrayList<>();
        String incrementIndex = indexUtil.incrementIndex(incomeDtoList);
        assertEquals("0", incrementIndex);
    }

}
