
package model;

import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Logger;


public class Task {

    private int id;
    private Date dueDate;
    private String subiect;
    private String status;
    private User user;

    public Task() {
    }

    public Task(String status) {
        this.status = status;
    }

    public Task(int id, String Subiect, String status) {
        this.id = id;
        this.dueDate = new Date(Calendar.getInstance().getTime().getTime());
        this.subiect = Subiect;
        this.status = status;
    }

    public Task(int id, Date dueDate, String subiect, String status) {
        this.id = id;
        this.dueDate = dueDate;
        this.subiect = subiect;
        this.status = status;
    }

    public Task(int id, Date dueDate, String subiect) {
        this.id = id;
        this.dueDate = dueDate;
        this.subiect = subiect;
    }

    public Task(Date dueDate, String subiect, String status, User user) {
        this.dueDate = dueDate;
        this.subiect = subiect;
        this.status = status;
        this.user = user;
    }

    public Task(int id, Date dueDate, String subiect, String status, User user) {
        this.id = id;
        this.dueDate = dueDate;
        this.subiect = subiect;
        this.status = status;
        this.user = user;
    }

    public Task(int id, String status) {
        this.id = id;
        this.status = status;
    }

    public Task( String subiect, String status, Date dueDate) {
        this.subiect = subiect;
        this.status = status;
        this.dueDate = dueDate;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date DueDate) {
        this.dueDate = DueDate;
    }

    public String getSubiect() {
        return subiect;
    }

    public void setSubiect(String Subiect) {
        this.subiect = Subiect;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return id + " " + dueDate + " " + subiect + " " + status + " ";
    }

}
