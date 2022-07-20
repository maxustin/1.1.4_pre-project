package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Sergey", "Nemchinsky", (byte) 40);
        userService.saveUser("German", "Sevostyanov", (byte) 28);
        userService.saveUser("Maxim", "Ustinov", (byte) 22);
        userService.saveUser("Vladislav", "Tsyrulnikov", (byte) 28);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        userService.dropUsersTable();

    }
}
