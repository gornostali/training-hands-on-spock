package me.igornostali.web.config;

import me.igornostali.data.UserRepository;
import me.igornostali.data.memory.InMemoryUserRepository;
import me.igornostali.service.UserService;
import me.igornostali.service.simple.SimpleUserService;
import me.igornostali.web.rest.UserRestService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

import javax.inject.Singleton;

/**
 * Context configuration for services
 * <p>
 * Created by igornostali on 4/27/2016.
 */
@Configuration
@PropertySource({"classpath:configuration.properties"})
public class ServerContextConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public UserRestService provideUserRestService(final UserService userService, final UserRepository userRepository) {
        return new UserRestService(userService, userRepository);
    }

    @Bean
    public UserService provideUserService(Environment environment) {
        return new SimpleUserService(environment, provideUserRepository());
    }

    @Bean
    @Singleton
    public UserRepository provideUserRepository() {
        return new InMemoryUserRepository();
    }
}