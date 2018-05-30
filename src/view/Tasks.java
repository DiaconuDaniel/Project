package view;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import model.Task;
import model.User;
import service.MainService;

public class Tasks extends javax.swing.JFrame {

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jList1 = new javax.swing.JList<model.Task>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        jList1.setToolTipText("");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null}
            },
            new String [] {
                "Due Date", "Subject", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton1.setText("BACK");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jButton1)
                .addContainerGap(164, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(28, 28, 28))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JList<model.Task> jList1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    public Tasks() {
        initComponents();
        
        //se selecteza un rand cu click stanga si se apasa de 2 ori click dreapta
        //pentru a se deschide fereastra WindowTasks
        jButton1.addActionListener(ev -> back());

        jTable1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    displayTask();

                }
            }
        }
        );

        setLocationRelativeTo(null);
        setVisible(true);
        populateTable();

    }

    private void populateTable() {
        DefaultTableModel dtm = new DefaultTableModel(0, 0);
        String header[] = new String[]{"Due Date", "Subject", "Status"};
        dtm.setColumnIdentifiers(header);
        jTable1.setModel(dtm);

        List<Task> allTasksForCurrentUser = MainService.getAllTasksForCurrentUser();

        for (int count = 0; count < allTasksForCurrentUser.size(); count++) {
            Task task = allTasksForCurrentUser.get(count);
            dtm.addRow(new Object[]{task.getDueDate(), task.getSubiect(), task.getStatus()});
        }
    }

    public void displayTask() {
        WindowTasks windowTask = new WindowTasks();

        int index = jTable1.getSelectedRow();
        TableModel model = jTable1.getModel();

        String dueDate = model.getValueAt(index, 0).toString();
        String subject = model.getValueAt(index, 1).toString();
        String status = model.getValueAt(index, 2).toString();

        windowTask.setVisible(true);
        windowTask.pack();
        windowTask.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        windowTask.jTextField1_dueDate.setText(dueDate);
        windowTask.jTextField2_Subject.setText(subject);
        windowTask.jTextField3_Subject.setText(status);
        dispose();
    }

    public void back() {
        new MainWindow();
        dispose();
    }

}
