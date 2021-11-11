/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package noevg.github.io.simplex.gui;

/**
 *
 * @author ziusudra
 */
public class SimplexGUI extends javax.swing.JFrame {

    /**
     * Creates new form SimplexGUI
     */
    public SimplexGUI() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        panelModel = new javax.swing.JPanel();
        labelTitleModel = new javax.swing.JLabel();
        scrollModel = new javax.swing.JScrollPane();
        panelButtonModel = new javax.swing.JPanel();
        captureModel = new javax.swing.JButton();
        loadModel = new javax.swing.JButton();
        panelSolver = new javax.swing.JPanel();
        buttonSolver = new javax.swing.JButton();
        panelMessage = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        messageLog = new javax.swing.JTextArea();
        progressBarSolver = new javax.swing.JProgressBar();
        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        menuGuide = new javax.swing.JMenu();
        menuAbout = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(500, 700));
        setPreferredSize(new java.awt.Dimension(500, 500));
        setSize(new java.awt.Dimension(500, 500));

        mainPanel.setBackground(new java.awt.Color(254, 254, 254));
        mainPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder("Simplex Solver")));
        mainPanel.setLayout(new java.awt.BorderLayout());

        panelModel.setBackground(new java.awt.Color(254, 254, 254));
        panelModel.setLayout(new java.awt.BorderLayout());

        labelTitleModel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitleModel.setText("Mathematical Model ");
        panelModel.add(labelTitleModel, java.awt.BorderLayout.NORTH);

        scrollModel.setMaximumSize(new java.awt.Dimension(250, 250));
        panelModel.add(scrollModel, java.awt.BorderLayout.CENTER);

        panelButtonModel.setBackground(new java.awt.Color(254, 254, 254));

        captureModel.setBackground(new java.awt.Color(1, 1, 1));
        captureModel.setForeground(new java.awt.Color(254, 254, 254));
        captureModel.setText("Capture Model");
        captureModel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelButtonModel.add(captureModel);

        loadModel.setBackground(new java.awt.Color(1, 1, 1));
        loadModel.setForeground(new java.awt.Color(254, 254, 254));
        loadModel.setText("Load Model");
        loadModel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelButtonModel.add(loadModel);

        panelModel.add(panelButtonModel, java.awt.BorderLayout.PAGE_END);

        mainPanel.add(panelModel, java.awt.BorderLayout.CENTER);

        panelSolver.setLayout(new java.awt.BorderLayout());

        buttonSolver.setBackground(new java.awt.Color(1, 1, 1));
        buttonSolver.setForeground(new java.awt.Color(254, 254, 254));
        buttonSolver.setText("Solver with Simplex");
        buttonSolver.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelSolver.add(buttonSolver, java.awt.BorderLayout.NORTH);

        panelMessage.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setPreferredSize(new java.awt.Dimension(300, 300));

        messageLog.setBackground(new java.awt.Color(1, 1, 1));
        messageLog.setColumns(20);
        messageLog.setForeground(new java.awt.Color(1, 209, 18));
        messageLog.setRows(5);
        messageLog.setText("Solver Simplex version 1.0");
        messageLog.setToolTipText("");
        jScrollPane2.setViewportView(messageLog);

        panelMessage.add(jScrollPane2, java.awt.BorderLayout.CENTER);
        panelMessage.add(progressBarSolver, java.awt.BorderLayout.SOUTH);

        panelSolver.add(panelMessage, java.awt.BorderLayout.CENTER);

        mainPanel.add(panelSolver, java.awt.BorderLayout.SOUTH);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        menuBar.setBackground(new java.awt.Color(254, 254, 254));
        menuBar.setBorder(null);

        menuFile.setText("File");

        jMenuItem1.setText("Load Model");
        menuFile.add(jMenuItem1);

        jMenuItem2.setText("Reset");
        menuFile.add(jMenuItem2);

        jMenuItem3.setText("Export Solution");
        menuFile.add(jMenuItem3);

        menuBar.add(menuFile);

        menuGuide.setText("Guide");
        menuBar.add(menuGuide);

        menuAbout.setText("About");
        menuBar.add(menuAbout);

        setJMenuBar(menuBar);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    //public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        // try {
        //    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
        //        if ("Nimbus".equals(info.getName())) {
        //            javax.swing.UIManager.setLookAndFeel(info.getClassName());
        //            break;
        //        }
        //    }
        // } catch (ClassNotFoundException ex) {
        //     java.util.logging.Logger.getLogger(SimplexGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        // } catch (InstantiationException ex) {
        //     java.util.logging.Logger.getLogger(SimplexGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        // } catch (IllegalAccessException ex) {
        //     java.util.logging.Logger.getLogger(SimplexGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        // } catch (javax.swing.UnsupportedLookAndFeelException ex) {
        //     java.util.logging.Logger.getLogger(SimplexGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        // }
        //</editor-fold>

        /* Create and display the form */
        // java.awt.EventQueue.invokeLater(new Runnable() {
        //     public void run() {
        //         new SimplexGUI().setVisible(true);
        //     }
        // });
    // }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonSolver;
    private javax.swing.JButton captureModel;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelTitleModel;
    private javax.swing.JButton loadModel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenu menuAbout;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuGuide;
    private javax.swing.JTextArea messageLog;
    private javax.swing.JPanel panelButtonModel;
    private javax.swing.JPanel panelMessage;
    private javax.swing.JPanel panelModel;
    private javax.swing.JPanel panelSolver;
    private javax.swing.JProgressBar progressBarSolver;
    private javax.swing.JScrollPane scrollModel;
    // End of variables declaration//GEN-END:variables
}
