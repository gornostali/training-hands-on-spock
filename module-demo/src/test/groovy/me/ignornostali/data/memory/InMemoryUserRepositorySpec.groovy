package me.ignornostali.data.memory

import me.ignornostali.model.User
import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit tests for {@link InMemoryUserRepository}
 *
 * Created by igornostali on 4/18/2016.
 */
@Unroll
class InMemoryUserRepositorySpec extends Specification {

    def "Add WITH a valid user SHOULD persists it into repository"() {
        setup:
        def database = Mock(Map.class);
        def repository = new InMemoryUserRepository(database: database);

        when:
        def result = repository.add(new User("John", "Smith"));

        then:
        assert result.getId()

        // Expect [put] to be called once
        1 * database.put(_ as Long, _ as User);
    }

    def "Add WITH an invalid user THROWS IllegalArgumentException exception"() {
        setup:
        def repository = new InMemoryUserRepository();

        when:
        repository.add(null);

        then:
        thrown(IllegalArgumentException.class);
    }

    def "Get WITH a user id [#userId] SHOULD return user [#user]"() {
        setup:
        def database = Stub(Map.class);
        def repository = new InMemoryUserRepository(database: database);

        and:
        database.get(userId) >> user;

        expect:
        repository.get(userId) == user;

        where:
        userId || user
        1      || new User("Alex", "Smith")
    }
}
