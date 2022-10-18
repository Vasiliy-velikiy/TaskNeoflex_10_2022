package moskalev.neoflexVacationCalculator.controller;

import lombok.AllArgsConstructor;
import moskalev.neoflexVacationCalculator.service.VacationService;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/calculacte")
public class VacationControllerImpl implements VacationController {
    private final VacationService vacationService;

    
    @GetMapping
    public String calculateMoney(@RequestParam("salary") int salary, @RequestParam("days") int days) {
        return vacationService.calculateMoney(salary, days);
    }

    //Для доп задания с учётом праздников и выходных (добавляются точные дни ухода в отпуск )
    @GetMapping("/pro")
    public String calculateMoney(@RequestParam("salary") int salary,
                                 @RequestParam("startDay") String startDay, @RequestParam("endDay") String endDay) {
        return vacationService.calculateMoneyPro(salary, startDay, endDay);
    }

}
