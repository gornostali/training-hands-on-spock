package me.ignornostali.model;

/**
 * Defines a simple model for a user entity
 * <p>
 * Created by igornostali on 4/15/2016.
 */
public class User {

    private long id;
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

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "User #" + this.id + " -> " + this.firstName + " " + this.lastName;
    }
}