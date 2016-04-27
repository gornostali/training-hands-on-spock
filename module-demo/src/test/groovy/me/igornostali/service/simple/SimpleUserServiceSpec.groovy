package me.igornostali.service.simple

import me.igornostali.data.UserRepository
import me.igornostali.model.User
import me.igornostali.service.exception.AlreadyExistsException
import me.igornostali.test.util.T
import org.springframework.core.env.Environment
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit tests for {@link SimpleUserService}
 *
 * Created by igornostali on 4/27/2016.
 */
@Unroll
class SimpleUserServiceSpec extends Specification {

    def "REGISTER with [#user] SHOULD throw exception"() {
        setup:
        def fakeEnvironment = Stub(Environment.class);
        def fakeRepository = Stub(UserRepository.class);
        def userService = new SimpleUserService(fakeEnvironment, fakeRepository);

        and:
        fakeRepository.getByEmail(_ as String) >> user

        when:
        userService.register(user);

        then:
        thrown(exception);

        where:
        user                                 || exception
        null                                 || IllegalArgumentException.class
        new User("John.Smith@somewhere.com") || AlreadyExistsException.class
    }

    def "REGISTER with ([#firstName]/[#lastName]) SHOULD register it"() {
        setup:
        def fakeEnvironment = Stub(Environment.class);
        def fakeRepository = Stub(UserRepository.class);
        def userService = new SimpleUserService(fakeEnvironment, fakeRepository);

        and:
        def user = T.userFor("John.Smith@somewhere.com", firstName, lastName);

        and:
        fakeEnvironment.getProperty(_ as String) >> defaultName
        fakeRepository.getByEmail(_ as String) >> null
        fakeRepository.add(_ as User) >> user

        when:
        def persisted = userService.register(user);

        then:
        assert persisted.getFirstName() == (firstName == null ? defaultName : firstName)
        assert persisted.getLastName() == (lastName == null ? defaultName : lastName)

        where:
        defaultName | firstName | lastName
        "default"   | null      | null
        "default"   | "John"    | "Smith"
    }
}
