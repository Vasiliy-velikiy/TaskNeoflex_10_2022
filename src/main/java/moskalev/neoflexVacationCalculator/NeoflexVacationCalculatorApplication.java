package moskalev.neoflexVacationCalculator;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.text.ParseException;


@SpringBootApplication
@EnableFeignClients("moskalev.neoflexVacationCalculator")
@EnableDiscoveryClient
public class NeoflexVacationCalculatorApplication {

	public static void main(String[] args) throws ParseException {
		SpringApplication.run(NeoflexVacationCalculatorApplication.class, args);
		System.out.println("ok");
	}
}
