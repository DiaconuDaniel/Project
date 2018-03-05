package service;

import connection.ConnectionManager;
import dao.TaskDao;
import java.sql.Connection;
import java.sql.SQLException;
import model.User;
import dao.UserDao;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Task;
import service.LoggedUserService;

public class MainService {

    public static boolean register(User user) {

        try (Connection con = ConnectionManager.getInstance().getConenction()) {
            UserDao userDao = new UserDao(con);
            boolean result = false;

            try {
                User user2 = userDao.findUser(user.getName());

                if (user2 == null) {
                    userDao.addUser(user);
                    result = true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return result;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;

    }

    public static User login(String name, String password) {

        try (Connection con = ConnectionManager.getInstance().getConenction()) {
            UserDao userDao = new UserDao(con);

            try {
                User user = userDao.findUser(name);

                if (user != null) {
                    if (user.getPassword().equals(password)) {
                        return user;
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Task> getAllTasksForCurrentUser() {

        try (Connection con = ConnectionManager.getInstance().getConenction()) {
            TaskDao taskDao = new TaskDao(con);

            User user = LoggedUserService.getInstance().getUser();
            try {
                return taskDao.getTasksByUserId(user.getId());
            } catch (SQLException ex) {
                Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, ex);
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<User> getAllUsers() {

        try (Connection con = ConnectionManager.getInstance().getConenction()) {
            UserDao userDao = new UserDao(con);

            try {

                return userDao.getAllUsers();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Task> getSearchAll(String name, String subject, Date date, String user_id) {

        try (Connection con = ConnectionManager.getInstance().getConenction()) {
            TaskDao taskDao = new TaskDao(con);
            UserDao userDao = new UserDao(con);

            try {
                User user1 = userDao.findUser(name);

                return taskDao.search(user1.getId(), subject, date);

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Task> populateWindowTask(String id) {

        try (Connection con = ConnectionManager.getInstance().getConenction()) {
            TaskDao taskDao = new TaskDao(con);

            try {

                return taskDao.populateTask();

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void update(Date date, String subject, String status, String name, Integer id) {

        try (Connection con = ConnectionManager.getInstance().getConenction()) {
            TaskDao taskDao = new TaskDao(con);
            try {
                UserDao userDao = new UserDao(con);
                User user = userDao.findUser(name);
                taskDao.updateTask(date, subject, status, user.getId(), id);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void addTask(String subject, String status, Date sqlDate, String userName) {

        try (Connection con = ConnectionManager.getInstance().getConenction()) {
            TaskDao taskDao = new TaskDao(con);

            try {

                UserDao userDao = new UserDao(con);
                User user = userDao.findUser(userName);
                Task task = new Task(sqlDate, subject, status, user);

                taskDao.addTask(task);

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
