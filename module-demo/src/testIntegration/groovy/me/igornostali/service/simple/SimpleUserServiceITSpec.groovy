package me.igornostali.service.simple

import me.igornostali.model.User
import me.igornostali.service.UserService
import me.igornostali.service.exception.AlreadyExistsException
import me.igornostali.test.config.ServiceTestContextConfig
import me.igornostali.test.util.T
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Integration tests for {@link SimpleUserService}
 *
 * Created by igornostali on 4/27/2016.
 */
@Unroll
@ContextConfiguration(classes = [ServiceTestContextConfig.class])
class SimpleUserServiceITSpec extends Specification {

    private static final String PROP_KEY_FIRST_NAME = "user.default.first.name";
    private static final String PROP_KEY_LAST_NAME = "user.default.last.name";

    @Autowired
    Environment environment;
    @Autowired
    UserService userService;

    def "REGISTER with [#user] SHOULD throw exception"() {
        when:
        this.userService.register(user);

        then:
        thrown(exception);

        where:
        user                                 || exception
        null                                 || IllegalArgumentException.class
        new User("John.Smith@somewhere.com") || AlreadyExistsException.class
    }

    def "REGISTER with ([#firstName]/[#lastName]) SHOULD register it"() {
        setup:
        def user = T.userFor("$firstName.$lastName@somewhere.com", firstName, lastName);

        when:
        def persisted = this.userService.register(user);

        then:
        assert persisted.getFirstName() == valueOrDefault(firstName, PROP_KEY_FIRST_NAME)
        assert persisted.getLastName() == valueOrDefault(lastName, PROP_KEY_LAST_NAME)

        where:
        firstName | lastName
        null      | null
        "Julie"   | "Smith"
    }

    def valueOrDefault(String value, String defaultPropertyKey) {
        return value == null ? this.environment.getProperty(defaultPropertyKey) : value
    }
}
