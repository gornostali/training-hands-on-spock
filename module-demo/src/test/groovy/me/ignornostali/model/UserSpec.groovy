package me.ignornostali.model

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit tests for {@link User}
 *
 * Created by igornostali on 4/15/2016.
 */
@Unroll
class UserSpec extends Specification {

    def "CONSTRUCTOR with no arguments EXPECT an empty object"() {
        when:
        def user = new User();

        then:
        user.getFirstName() == null
        user.getLastName() == null
    }

    def "CONSTRUCTOR with arguments([#firstName],[#lastName]) EXPECT same values in object properties"() {
        setup:
        def user = new User(firstName, lastName);

        expect:
        user.getFirstName().equals(firstName)
        user.getLastName().equals(lastName)

        where:
        firstName | lastName
        "Alex"    | "Bush"
        "Bob"     | "Smith"
    }

    def "SET and GET with arguments([#firstName]/[#lastName]) SHOULD return same values"() {
        setup:
        def user = new User();

        and:
        user.setFirstName(firstName);
        user.setLastName(lastName);

        expect:
        user.getFirstName().equals(firstName);
        user.getLastName().equals(lastName);

        where:
        firstName | lastName
        "Alex"    | "Bush"
        "Bob"     | "Smith"
    }
}