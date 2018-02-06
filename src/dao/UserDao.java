
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.User;


public class UserDao {

    private Connection con;

    private PreparedStatement stmt1, stmt2, stmt3;

    public UserDao(Connection con) {
        try {
            this.con = con;
            this.stmt1 = con.prepareStatement("SELECT * FROM user WHERE name = ?");
            this.stmt2 = con.prepareStatement("INSERT INTO user VALUES (NULL,?,?)");
            this.stmt3 = con.prepareStatement("SELECT * FROM user");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public User findUser(String name) throws SQLException {
        stmt1.setString(1, name);
        User user = null;

        try (ResultSet rs = stmt1.executeQuery()) {
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setPassword(rs.getString("password"));
            }
        }

        return user;

    }

    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();

        try (ResultSet rs = stmt3.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameUser = rs.getString("name");
                users.add(new User(id, nameUser));

            }
        }

        return users;
    }

    public void addUser(User user) throws SQLException {
        stmt2.setString(1, user.getName());
        stmt2.setString(2, user.getPassword());
        stmt2.executeUpdate();
    }

}
