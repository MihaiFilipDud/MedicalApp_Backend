package ro.tuc.ds2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.validation.annotation.Validated;
import ro.tuc.ds2020.web.HelloWorldRMI;

import java.util.TimeZone;

@SpringBootApplication
@Validated
public class Ds2020Application {



	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
		SpringApplication.run(Ds2020Application.class, args);


	}
}
