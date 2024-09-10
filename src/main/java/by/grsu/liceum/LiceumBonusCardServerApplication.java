package by.grsu.liceum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LiceumBonusCardServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiceumBonusCardServerApplication.class, args);
	}

}
