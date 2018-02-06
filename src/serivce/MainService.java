package service;

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
import serivce.LoggedUserService;

public class MainService {

    private String user = "root";
    private String pass = "";
    private String url = "jdbc:mysql://localhost/gestionarea sarcinilor";
    private Connection con;

    private MainService() {
        try {
            con = DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final class SingletonHolder {

        private static final MainService INSTANCE = new MainService();
    }

    public static MainService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public boolean register(User user) {
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
    }

    public User login(String name, String password) {
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
    }

    public List<Task> getAllTasksForCurrentUser() {
        TaskDao taskDao = new TaskDao(con);

        User user = LoggedUserService.getInstance().getUser();
        try {
            return taskDao.getTasksByUserId(user.getId());
        } catch (SQLException ex) {
            Logger.getLogger(MainService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public List<User> getAllUsers() {
        UserDao userDao = new UserDao(con);

        try {

            return userDao.getAllUsers();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Task> getSearchAll(String name, String subject, Date date, String user_id) {
        TaskDao taskDao = new TaskDao(con);
        UserDao userDao = new UserDao(con);

        try {
            User user1 = userDao.findUser(name);

            return taskDao.search(user1.getId(), subject, date);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<Task> populateWindowTask(String id) {
        TaskDao taskDao = new TaskDao(con);

        try {

            return taskDao.populateTask();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public void update(Date date, String subject, String status, String name, Integer id) {
        TaskDao taskDao = new TaskDao(con);
        try {
            UserDao userDao = new UserDao(con);
            User user = userDao.findUser(name);
            taskDao.updateTask(date, subject, status, user.getId(), id);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(String subject, String status, Date sqlDate, String userName) {
        TaskDao taskDao = new TaskDao(con);

        try {

            UserDao userDao = new UserDao(con);
            User user = userDao.findUser(userName);
            Task task = new Task(sqlDate, subject, status, user);

            taskDao.addTask(task);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
