package me.igornostali.service;

import me.igornostali.model.User;
import me.igornostali.service.exception.AlreadyExistsException;

/**
 * Defines behaviour for a user service
 * <p>
 * Created by igornostali on 4/27/2016.
 */
public interface UserService {

    User register(final User user) throws AlreadyExistsException;
}
