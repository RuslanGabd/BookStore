package com.ruslan.controller.webExceptions;

public class UserExistException extends Exception {

    private String username;

    public UserExistException(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
