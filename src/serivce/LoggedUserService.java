
package serivce;

import model.User;


public class LoggedUserService {

    private User user;

    private LoggedUserService() {

    }

    private static final class SingletonHolder {

        private static final LoggedUserService INSTANCE = new LoggedUserService();
    }

    public static LoggedUserService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
