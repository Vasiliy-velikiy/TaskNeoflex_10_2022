package moskalev.neoflexVacationCalculator.service;

import org.springframework.web.bind.annotation.RequestParam;

public interface VacationService {
    String calculateMoney(int salary,int days);
    String calculateMoneyPro(int salary,String startday,String endDay);
}
