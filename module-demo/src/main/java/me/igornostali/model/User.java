package me.igornostali.model;

/**
 * Defines a simple model for a user entity
 * <p>
 * Created by igornostali on 4/15/2016.
 */
public class User {

    private long id;
    private String firstName;
    private String lastName;
    private String email;

    public User(final String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public String toString() {
        return "User #" + this.email + " -> " + this.firstName + " " + this.lastName;
    }
}