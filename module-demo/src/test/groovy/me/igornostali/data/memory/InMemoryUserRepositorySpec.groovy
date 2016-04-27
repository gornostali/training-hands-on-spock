package me.igornostali.data.memory

import me.igornostali.model.User
import me.igornostali.test.util.T
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit tests for {@link InMemoryUserRepository}
 *
 * Created by igornostali on 4/18/2016.
 */
@Unroll
class InMemoryUserRepositorySpec extends Specification {

    def "ADD with a valid user SHOULD persists it into repository"() {
        setup:
        def email = "John.Smith@somewhere.com";
        def fakeDatabase = Mock(Map.class);
        def repository = new InMemoryUserRepository(database: fakeDatabase);

        when:
        def result = repository.add(new User(email));

        then:
        assert result.getId()

        // Expect [put] to be called once
        1 * fakeDatabase.put(email, _ as User);
    }

    def "ADD with an invalid user THROWS IllegalArgumentException exception"() {
        setup:
        def repository = new InMemoryUserRepository();

        when:
        repository.add(null);

        then:
        thrown(IllegalArgumentException.class);
    }

    def "GET with a user id [#email] SHOULD return user [#user]"() {
        setup:
        def fakeDatabase = Stub(Map.class);
        def repository = new InMemoryUserRepository(database: fakeDatabase);

        and:
        fakeDatabase.get(email) >> user;

        expect:
        repository.getByEmail(email) == user;

        where:
        email                      || user
        "John.Smith@somewhere.com" || null
        "John.Smith@somewhere.com" || T.userFor("John.Smith@somewhere.com", "John", "Smith")
    }
}
