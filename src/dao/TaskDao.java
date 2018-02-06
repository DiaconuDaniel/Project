package dao;

import java.lang.reflect.Array;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Task;
import model.User;

public class TaskDao {

    private Connection con;

    private PreparedStatement stmt1, stmt2, stmt3, stmt4, stmt5;

    public TaskDao(Connection con) {
        try {
            this.con = con;
            stmt1 = con.prepareStatement("INSERT INTO task VALUES (NULL,?,?,?,?)");
            stmt2 = con.prepareStatement("SELECT * FROM task WHERE user_id = ? ORDER BY dueDate DESC ");
            stmt3 = con.prepareStatement("SELECT user.id AS user_id, user.name, task.id as task_id, task.dueDate, task.subject, task.status FROM task join user on user.id = task.user_id WHERE user_id = ? AND lower(subject) like lower (?) AND dueDate = DATE(?) ORDER BY dueDate DESC");
            stmt4 = con.prepareStatement("SELECT id, dueDate, subject, status FROM task");
            stmt5 = con.prepareStatement("UPDATE task INNER JOIN user on task.user_id = user.id SET task.dueDate = ?, task.subject = ?, task.status = ?, task.user_id = ? WHERE task.id = ?");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addTask(Task task) throws SQLException {
        stmt1.setDate(1, task.getDueDate());
        stmt1.setString(2, task.getSubiect());
        stmt1.setString(3, task.getStatus());
        stmt1.setInt(4, task.getUser().getId());
        stmt1.executeUpdate();
    }

    public List<Task> getTasksByUserId(Integer userId) throws SQLException {
        stmt2.setInt(1, userId);

        List<Task> tasks = new ArrayList<Task>();
        try (ResultSet rs = stmt2.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                Date dueDate = rs.getDate("dueDate");
                String subject = rs.getString("subject");
                String status = rs.getString("status");

                tasks.add(new Task(id, dueDate, subject, status));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;

    }

    public List<Task> search(Integer user_id, String subject, Date date) throws SQLException {
        stmt3.setInt(1, user_id);
        stmt3.setString(2, "%" + subject + "%");
        stmt3.setDate(3, date);

        List<Task> tasks = new ArrayList<>();

        try (ResultSet rs = stmt3.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("user_id");
                String nameU = rs.getString("name");
                int taskId = rs.getInt("task_id");
                Date dueDate = rs.getDate("dueDate");
                String subjectTask = rs.getString("subject");
                String status = rs.getString("status");
                User user = new User(id, nameU);

                tasks.add(new Task(taskId, dueDate, subjectTask, status, user));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tasks;
    }

    public List<Task> populateTask() throws SQLException {

        List<Task> tasks = new ArrayList<>();

        try (ResultSet rs = stmt4.executeQuery()) {
            while (rs.next()) {
                int idTask = rs.getInt("id");
                Date dueDate = rs.getDate("dueDate");
                String subjectTask = rs.getString("subject");
                String statusTask = rs.getString("status");

                tasks.add(new Task(idTask, dueDate, subjectTask, statusTask));

            }
        }
        return tasks;
    }

    public void updateTask(Date dueDate, String subject, String status, Integer userId, Integer id) throws SQLException {
        stmt5.setDate(1, dueDate);
        stmt5.setString(2, subject);
        stmt5.setString(3, status);
        stmt5.setInt(4, userId);
        stmt5.setInt(5, id);
        stmt5.executeUpdate();
    }
}
