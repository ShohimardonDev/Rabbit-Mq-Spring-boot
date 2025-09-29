package uz.ssh.rabbitmqspringboot.config;

import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

/**
 * @author Shokhimardon
 * @since 9/16/25
 */
@Configuration
public class JavaFakerConf {

    @Bean
    public Faker javaFake() {
        return new Faker(Locale.ENGLISH);
    }
}
