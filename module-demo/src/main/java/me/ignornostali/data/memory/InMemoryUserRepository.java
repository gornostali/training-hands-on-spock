package me.ignornostali.data.memory;

import me.ignornostali.data.UserRepository;
import me.ignornostali.model.User;

import java.util.HashMap;
import java.util.Map;

/**
 * In memory user repository
 * <p>
 * Created by igornostali on 4/18/2016.
 */
public class InMemoryUserRepository implements UserRepository {

    private Map<Long, User> database = new HashMap<>();

    @Override
    public User add(final User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");

        user.setId(System.currentTimeMillis());

        this.database.put(user.getId(), user);

        return user;
    }

    @Override
    public User get(final Long userId) {
        return this.database.get(userId);
    }
}
