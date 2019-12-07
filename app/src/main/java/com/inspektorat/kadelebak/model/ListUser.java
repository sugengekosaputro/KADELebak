package com.inspektorat.kadelebak.model;

import java.util.ArrayList;
import java.util.List;

public class ListUser {

    private List<User> users;

    public ListUser() {
        this.users = new ArrayList<>();

        User user1 = new User("Jim", 20, "jim@gmail.com");
        this.users.add(user1);
        User user2 = new User("John", 23, "john@gmail.com");
        this.users.add(user2);
        User user3 = new User("Jenny", 25, "jenny@gmail.com");
        this.users.add(user3);
    }

    public List<User> getUsers() {
        return users;
    }
}
