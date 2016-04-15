package me.ignornostali.model

import spock.lang.Specification

/**
 * Unit tests for {@link User}
 *
 * Created by igornostali on 4/15/2016.
 */
class UserSpec extends Specification {

    def "CONSTRUCTOR with no arguments EXPECT an empty object"() {
        when:
        def user = new User();

        then:
        user.getFirstName() == null
        user.getLastName() == null
    }
}