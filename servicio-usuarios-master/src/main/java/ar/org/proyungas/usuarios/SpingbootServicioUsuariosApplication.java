package ar.org.proyungas.usuarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.domain.EntityScan;

//@EntityScan({"com.formacionbdi.springboot.app.usuarios.commons.models.entity"})
@SpringBootApplication(scanBasePackages = {
		"gov.justucuman.usuarios",
		"ar.org.proyungas.usuarios"
})
public class SpingbootServicioUsuariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpingbootServicioUsuariosApplication.class, args);
	}

}
