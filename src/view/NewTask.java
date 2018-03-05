package view;

import dao.UserDao;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;
import model.Task;
import model.User;
import service.LoggedUserService;
import service.MainService;

public class NewTask extends javax.swing.JFrame {

    public NewTask() {
        initComponents();

        populate();

        jButton1.addActionListener(ev -> {
            try {
                addTask();
            } catch (ParseException ex) {
                Logger.getLogger(NewTask.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        jButton2.addActionListener(ev -> back());

        jTextField3_subject.setDocument(new NewTask.JTextFieldLimit(100));

        setVisible(true);
        setLocationRelativeTo(null);
    }

    public void populate() {
        List<User> users = MainService.getAllUsers();
        User user = LoggedUserService.getInstance().getUser();
        jComboBox1_assigned.addItem(user);

        for (int i = 0; i < users.size(); i++) {
            User user2 = users.get(i);

            if (!user.getName().equals(user2.getName())) {
                jComboBox1_assigned.addItem(user2);
            }
        }

        jComboBox1.setModel(new DefaultComboBoxModel(new String[]{"NEW", "IN_PROGRESS", "DONE"}));
    }

    public void addTask() throws ParseException {
        String subject = jTextField3_subject.getText();

        String status = (String) jComboBox1.getSelectedItem();

        Date date = jDateChooser1.getDate();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String userName = jComboBox1_assigned.getSelectedItem().toString();

        MainService.addTask(subject, status, sqlDate, userName);

        JOptionPane.showMessageDialog(rootPane, "Task added! ");
        dispose();
        new MainWindow();
    }

    public void back() {
        dispose();
        new MainWindow();
    }

    class JTextFieldLimit extends PlainDocument {

        private int limit;

        JTextFieldLimit(int limit) {
            super();
            this.limit = limit;
        }

        JTextFieldLimit(int limit, boolean upper) {
            super();
            this.limit = limit;
        }

        public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
            if (str == null) {
                return;
            }

            if ((getLength() + str.length()) <= limit) {
                super.insertString(offset, str, attr);
            }
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1_Subject = new javax.swing.JLabel();
        jLabel2_dueDate = new javax.swing.JLabel();
        jComboBox1_assigned = new javax.swing.JComboBox();
        jLabel3_Status = new javax.swing.JLabel();
        jLabel4_assigned = new javax.swing.JLabel();
        jTextField3_subject = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1_Subject.setText("Subject:");

        jLabel2_dueDate.setText("Due Date:");

        jLabel3_Status.setText("Status:");

        jLabel4_assigned.setText("Assigned:");

        jButton1.setText("Add Task");

        jButton2.setText("BACK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1_Subject)
                            .addComponent(jLabel4_assigned))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3_subject, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jButton1)
                            .addComponent(jComboBox1_assigned, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2_dueDate)
                            .addComponent(jLabel3_Status))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(143, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1_Subject)
                    .addComponent(jTextField3_subject, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3_Status)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2_dueDate)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4_assigned)
                    .addComponent(jComboBox1_assigned, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 36, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox1_assigned;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1_Subject;
    private javax.swing.JLabel jLabel2_dueDate;
    private javax.swing.JLabel jLabel3_Status;
    private javax.swing.JLabel jLabel4_assigned;
    private javax.swing.JTextField jTextField3_subject;
    // End of variables declaration//GEN-END:variables
}
