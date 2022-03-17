package config;

import com.gmail.shepard1992.familybudgetv1.repository.api.RepositoryData;
import com.gmail.shepard1992.familybudgetv1.service.api.TotalService;
import com.gmail.shepard1992.familybudgetv1.service.impl.CostServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.impl.IncomeServiceImpl;
import com.gmail.shepard1992.familybudgetv1.service.model.Cost;
import com.gmail.shepard1992.familybudgetv1.service.model.Income;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.CostDto;
import com.gmail.shepard1992.familybudgetv1.service.model.dto.IncomeDto;
import com.gmail.shepard1992.familybudgetv1.utils.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TotalServiceConfigTest {

    @Bean
    public TotalService getTotalIncomeServiceBean(ValidationUtil validationUtil, RepositoryData<Income> repositoryData, MapperUtil mapperUtil, IndexUtil<IncomeDto> indexUtil, TotalServiceUtil totalServiceUtil, DeleteRowUtil<Income> deleteRowUtil) {
        return new IncomeServiceImpl(validationUtil, repositoryData, mapperUtil, indexUtil, totalServiceUtil, deleteRowUtil);
    }

    @Bean
    public TotalService getTotalCostServiceBean(ValidationUtil validationUtil, RepositoryData<Cost> repositoryData, MapperUtil mapperUtil, IndexUtil<CostDto> indexUtil, TotalServiceUtil totalServiceUtil, DeleteRowUtil<Cost> deleteRowUtil) {
        return new CostServiceImpl(validationUtil, repositoryData, mapperUtil, indexUtil, totalServiceUtil, deleteRowUtil);
    }

}
