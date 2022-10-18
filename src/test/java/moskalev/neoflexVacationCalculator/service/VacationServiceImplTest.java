package moskalev.neoflexVacationCalculator.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class VacationServiceImplTest {

    int salary=30000;
    int paidDays=7;
    String expectedAnswer="7167,24";
    @Autowired
    private VacationService vacationService;
    @Test
    void calculateMoney() {
       String actual= vacationService.calculateMoney(salary,paidDays);
        assertEquals(expectedAnswer,actual);
    }

    @Test
    void calculateMoneyPro() {
      String actual =vacationService.calculateMoneyPro(30000,"2022-10-26","2022-11-03");
        assertEquals(expectedAnswer,actual);
    }
}