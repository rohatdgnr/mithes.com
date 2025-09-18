package contact_form_backend;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title="Contact",version = "1.0",description = "Contact"))
public class ContactFormBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContactFormBackendApplication.class, args);
	}

}
