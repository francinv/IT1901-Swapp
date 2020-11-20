package swapp.restserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import swapp.json.SwappModule;

@SpringBootApplication
public class SwappServer {

  @Bean
  public SwappModule objectMapperModule() {
    return new SwappModule();
  }

  public static void main(String[] args) {
    SpringApplication.run(SwappServer.class, args);
  }

}
