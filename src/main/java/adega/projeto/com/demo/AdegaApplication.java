package adega.projeto.com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AdegaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdegaApplication.class, args);
	}


}
