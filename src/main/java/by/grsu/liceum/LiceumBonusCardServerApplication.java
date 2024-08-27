package by.grsu.liceum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class LiceumBonusCardServerApplication {

	public static void main(String[] args) {
		for (int i = 0; i < 10; ++i){
			System.out.println(UUID.randomUUID().toString());
		}


		SpringApplication.run(LiceumBonusCardServerApplication.class, args);
	}

}
