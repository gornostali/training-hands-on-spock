package me.ignornostali.model;

/**
 * Defines a simple model for a user entity
 * <p>
 * Created by igornostali on 4/15/2016.
 */
public class User {

    private String firstName;
    private String lastName;

    public User() {
    }

    public User(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}