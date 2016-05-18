package me.igornostali.service.simple;

import me.igornostali.data.UserRepository;
import me.igornostali.model.User;
import me.igornostali.service.UserService;
import me.igornostali.service.exception.AlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Simple user service
 * <p>
 * Created by igornostali on 4/27/2016.
 */
@Service
public class SimpleUserService implements UserService {

    private static final String PROP_KEY_DEFAULT_FIRST_NAME = "user.default.first.name";
    private static final String PROP_KEY_DEFAULT_LAST_NAME = "user.default.last.name";

    private final Environment environment;
    private final UserRepository userRepository;

    @Autowired
    public SimpleUserService(final Environment environment, final UserRepository userRepository) {
        this.environment = environment;
        this.userRepository = userRepository;
    }

    @Override
    public User register(final User user) throws AlreadyExistsException {
        if (user == null) throw new IllegalArgumentException("User cannot be null");

        final User persisted = this.userRepository.getByEmail(user.getEmail());
        if (persisted != null) {
            throw new AlreadyExistsException(String.format("User with [%s] already exists", user.getEmail()));
        }

        final String defaultOrGivenFirstName = valueOrDefault(
                user.getFirstName(),
                this.environment.getProperty(PROP_KEY_DEFAULT_FIRST_NAME)
        );
        final String defaultOrGivenLastName = valueOrDefault(
                user.getLastName(),
                this.environment.getProperty(PROP_KEY_DEFAULT_LAST_NAME)
        );

        user.setFirstName(defaultOrGivenFirstName);
        user.setLastName(defaultOrGivenLastName);

        return this.userRepository.add(user);
    }

    private String valueOrDefault(String value, String defaultValue) {
        return StringUtils.isEmpty(value) ? defaultValue : value;
    }
}
