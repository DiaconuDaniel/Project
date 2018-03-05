package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
 private String user;
    private String pass;
    private String url;

    private ConnectionManager() {
        this.url = "jdbc:mysql://localhost/gestionarea sarcinilor";
        this.user = "root";
        this.pass = "";

    }

    private static final class SingletonHolder {

        private static final ConnectionManager SINGLETON = new ConnectionManager();
    }

    public static ConnectionManager getInstance() {
        return SingletonHolder.SINGLETON;
    }

    public Connection getConenction() throws SQLException {
        return DriverManager.getConnection(url, user, pass);
    }
    
}
