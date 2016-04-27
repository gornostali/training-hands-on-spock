package me.igornostali.data;

import me.igornostali.model.User;

/**
 * Defines behaviour for a user repository
 * <p>
 * Created by igornostali on 4/18/2016.
 */
public interface UserRepository {

    User add(final User user);

    User getByEmail(final String email);
}
