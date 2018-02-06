package view;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import com.sun.xml.internal.ws.api.message.Packet;
import dao.TaskDao;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation.Status;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.text.MaskFormatter;
import model.Task;
import model.User;
import serivce.LoggedUserService;

import service.MainService;

public class Search extends javax.swing.JFrame {

    public Search() {
        initComponents();
        jButton2.addActionListener(ev -> close());

        populateAssignedTo();
        jButton1.addActionListener(ev -> getSearch());

        //se selecteza un rand cu click stanga si se apasa de 2 ori click dreapta
        //pentru a se deschide fereastra WindowSearch
        jTable1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    getValues();
                }
            }
        });

        setVisible(true);
        setLocationRelativeTo(null);

    }

    public void populateAssignedTo() {
        List<User> users = MainService.getInstance().getAllUsers();
        User user = LoggedUserService.getInstance().getUser();
        jComboBox1.addItem(user);

        for (int i = 0; i < users.size(); i++) {
            User user2 = users.get(i);

            if (!user.getName().equals(user2.getName())) {
                jComboBox1.addItem(user2);
            }
        }

    }

    public void getSearch() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);

        String header[] = new String[]{"Due Date", "Subject", "Status", "Assigned", "taskId"};
        dtm.setColumnIdentifiers(header);
        jTable1.setModel(dtm);

        jTable1.removeColumn(jTable1.getColumnModel().getColumn(4));

        String name = jComboBox1.getSelectedItem().toString().toString();
        String text = jTextField4.getText();
        Date date = jDateChooser2.getDate();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        List<Task> getAll = MainService.getInstance().getSearchAll(name, text, sqlDate, name.toString());

        for (int count = 0; count < getAll.size(); count++) {
            Task task = getAll.get(count);
            dtm.addRow(new Object[]{task.getDueDate(), task.getSubiect(), task.getStatus(), task.getUser().getName(), task.getId()});
        }

    }

    public void getValues() {

        WindowSearch windowSearch = new WindowSearch();
        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();

        String data = model.getValueAt(index, 0).toString();
        String subject = model.getValueAt(index, 1).toString();

        String status = model.getValueAt(index, 2).toString();
        Integer id = (Integer) model.getValueAt(index, 4);

        windowSearch.setVisible(true);
        windowSearch.pack();
        windowSearch.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        windowSearch.jTextField3_dueDate.setText(data);
        windowSearch.jTextField2_Subject.setText(subject);

        windowSearch.jComboBox1_STATUS.setModel(new DefaultComboBoxModel(new String[]{"NEW", "IN_PROGRESS", "DONE"}));
        windowSearch.jComboBox1_STATUS.setSelectedItem(status);
        windowSearch.jTextField1_ID_task.setText(id.toString());

    }

    public void close() {
        new MainWindow();
        dispose();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox();
        jTextField4 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Due Date", "Subject", "Status", "Assigned"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setText("Assigned To:");

        jLabel2.setText("Subject:");

        jLabel3.setText("Due date after:");

        jButton1.setText("Search");

        jButton2.setText("Back");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3))
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jComboBox1, 0, 164, Short.MAX_VALUE)
                                    .addComponent(jTextField4)
                                    .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addComponent(jButton2)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(jButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 418, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 387, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 23, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    public javax.swing.JComboBox jComboBox1;
    public com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables

}
