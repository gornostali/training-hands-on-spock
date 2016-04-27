package me.igornostali.model

import spock.lang.Specification
import spock.lang.Unroll

/**
 * Unit tests for {@link User}
 *
 * Created by igornostali on 4/15/2016.
 */
@Unroll
class UserSpec extends Specification {

    def "CONSTRUCTOR with arguments([#email]) EXPECT same value in object properties"() {
        setup:
        def user = new User(email);

        expect:
        user.getEmail().equals(email)

        where:
        email << [
                "something@anywhere.com",
                "anything@anywhere.com"
        ]
    }

    def "SET and GET with arguments([#id]/[#firstName]/[#lastName]) SHOULD return same values"() {
        setup:
        def user = new User();

        and:
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);

        expect:
        user.getId() == id;
        user.getFirstName().equals(firstName);
        user.getLastName().equals(lastName);

        where:
        id | firstName | lastName
        1  | "Alex"    | "Bush"
        2  | "Bob"     | "Smith"
    }
}