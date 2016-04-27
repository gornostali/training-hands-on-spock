package me.igornostali.test.util;

import me.igornostali.model.User;

/**
 * Test utils
 * <p>
 * Created by igornostali on 4/27/2016.
 */
public final class T {

    public static User userFor(String email, String firstName, String lastName) {
        User user = new User(email);

        user.setFirstName(firstName);
        user.setLastName(lastName);

        return user;
    }
}
