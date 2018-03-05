package view;

public class MainWindow extends javax.swing.JFrame {

    public MainWindow() {
        initComponents();

        jMenuItemFo.addActionListener(ev -> logout());
        jMenuItem2.addActionListener(ev -> tasks());
        jMenuItem3.addActionListener(ev -> search());
        jMenuItem1_task.addActionListener(ev -> addTask());

        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void logout() {
        dispose();
        new LoginFrame();
    }

    public void tasks() {
        dispose();
        new Tasks();
    }

    public void search() {
        dispose();
        new Search();
    }

    public void addTask() {
        dispose();
        new NewTask();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1_task = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemFo = new javax.swing.JMenuItem();

        jMenu2.setText("File");
        jMenuBar2.add(jMenu2);

        jMenu3.setText("Edit");
        jMenuBar2.add(jMenu3);

        jMenu6.setText("jMenu6");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/hello1.jpg"))); // NOI18N

        jMenuBar1.setForeground(new java.awt.Color(255, 0, 102));
        jMenuBar1.setFont(new java.awt.Font("Yu Gothic Medium", 3, 14)); // NOI18N

        jMenu4.setText("My Tasks");
        jMenu4.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jMenuItem2.setText("Tasks");
        jMenu4.add(jMenuItem2);

        jMenuItem1_task.setText("New Task");
        jMenu4.add(jMenuItem1_task);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Search");
        jMenu5.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jMenuItem3.setText("Search");
        jMenu5.add(jMenuItem3);

        jMenuBar1.add(jMenu5);

        jMenu1.setText("Exit");
        jMenu1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N

        jMenuItemFo.setText("Logout");
        jMenu1.add(jMenuItemFo);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1_task;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItemFo;
    // End of variables declaration//GEN-END:variables
}
