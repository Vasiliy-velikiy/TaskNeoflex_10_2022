package moskalev.neoflexVacationCalculator.feignClient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


/**
 * Клиент для получения буднего или праздничного дня
 */
@FeignClient(name = "dayoff", url = "${whatisday.url}")
public interface WeekendsAndWeekdaysFeignClient {
    @GetMapping("/{date}")
    ResponseEntity<String>  whatIsDay(@PathVariable("date") String date);
}
