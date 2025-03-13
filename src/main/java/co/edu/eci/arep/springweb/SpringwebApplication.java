package co.edu.eci.arep.springweb;

import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class SpringwebApplication {

	public static void main(String[] args) {
		try {
			Dotenv dotenv = Dotenv.configure().directory("./").load(); // Fuerza la carga del .env
			System.out.println("Cargando archivo .env desde: " + dotenv.toString());
		
			System.setProperty("DB_URL", dotenv.get("DB_URL"));
			System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
			System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
		
		} catch (Exception e) {
			System.out.println("No .env file found, using system environment variables.");
			e.printStackTrace();
		}
		System.out.println("DB_URL: " + System.getProperty("DB_URL")); // Para verificar que se cargó correctamente
		if (System.getProperty("DB_URL")==null){
			System.setProperty("DB_URL", "jdbc:mysql://mysql:3306/mi_base_de_datos?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC");
			System.setProperty("DB_USERNAME", "root");
			System.setProperty("DB_PASSWORD", "rootpassword");
		}
		System.out.println("DB_URL: " + System.getProperty("DB_URL")); // Para verificar que se cargó correctamente
		SpringApplication app = new SpringApplication(SpringwebApplication.class);
		app.setDefaultProperties(Collections.singletonMap("server.port", "8080"));
		app.run(args); 
	}

}
