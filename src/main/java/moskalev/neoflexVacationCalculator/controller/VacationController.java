package moskalev.neoflexVacationCalculator.controller;

import org.springframework.web.bind.annotation.RequestParam;

public interface VacationController {
    String calculateMoney( int salary, int days);
    String calculateMoney(int salary, String startDay, String endDay);
}
