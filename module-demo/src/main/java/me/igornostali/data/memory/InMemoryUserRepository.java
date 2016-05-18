package me.igornostali.data.memory;

import me.igornostali.data.UserRepository;
import me.igornostali.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * In memory user repository
 * <p>
 * Created by igornostali on 4/18/2016.
 */
public class InMemoryUserRepository implements UserRepository {

    private Map<String, User> database = new HashMap<>();

    @Override
    public User add(final User user) {
        if (user == null) throw new IllegalArgumentException("User cannot be null");

        user.setId(System.currentTimeMillis());

        this.database.put(user.getEmail(), user);

        return user;
    }

    @Override
    public User[] getAll() {
        final Collection<User> users = this.database.values();

        return users.toArray(new User[users.size()]);
    }

    @Override
    public User getByEmail(final String email) {
        return this.database.get(email);
    }
}
