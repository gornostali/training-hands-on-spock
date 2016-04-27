package me.igornostali.test.config;

import me.igornostali.data.UserRepository;
import me.igornostali.data.memory.InMemoryUserRepository;
import me.igornostali.service.UserService;
import me.igornostali.service.simple.SimpleUserService;
import me.igornostali.test.util.T;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

/**
 * Test context configuration for services
 * <p>
 * Created by igornostali on 4/27/2016.
 */
@Configuration
@PropertySource({"classpath:test-configuration.properties"})
public class ServiceTestContextConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public UserService provideUserService(Environment environment) {
        return new SimpleUserService(environment, provideUserRepository());
    }

    @Bean
    public UserRepository provideUserRepository() {
        final UserRepository repository = new InMemoryUserRepository();

        // Add a few test users to repository
        repository.add(T.userFor("John.Smith@somewhere.com", "John", "Smith"));
        repository.add(T.userFor("Mike.Smith@somewhere.com", "Mike", "Smith"));
        repository.add(T.userFor("Paul.Smith@somewhere.com", "Paul", "Smith"));

        return repository;
    }
}