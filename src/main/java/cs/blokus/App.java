package cs.blokus;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class App 
{
	 /**
     * Deploys the Spring application inside an embedded Tomcat
     *
     * @param args args
     */
	
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
    
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
