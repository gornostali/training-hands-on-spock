package me.ignornostali.data;

import me.ignornostali.model.User;

/**
 * Defines a simple user repository
 * <p>
 * <p>
 * Created by igornostali on 4/18/2016.
 */
public interface UserRepository {

    User add(final User user);

    User get(final Long userId);
}
