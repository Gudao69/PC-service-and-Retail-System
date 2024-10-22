/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PCService;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author msi
 */
public class LoginForm extends javax.swing.JFrame {

    Connection con = null;
    ResultSet rs=null;
    Statement stmt = null;
    String url = "jdbc:mysql://localhost:3306/PCS";
    String username = "root";
    String password = "Ehedenandayo";
    
    public LoginForm() {
        initComponents();
        Image();
        Open();
        Close();
        lblOff.setVisible(false);
        getRootPane().setDefaultButton(btnLogin);
    }

    private void Image()
    {
        Icon i = lblImage.getIcon();
        ImageIcon icon = (ImageIcon)i;
        Image scale = icon.getImage().getScaledInstance(lblImage.getWidth(),lblImage.getHeight(),Image.SCALE_SMOOTH);
        lblImage.setIcon(new ImageIcon(scale));
    }
    private void Open()
    {
        Icon i = lblOn.getIcon();
        ImageIcon icon = (ImageIcon)i;
        Image scale = icon.getImage().getScaledInstance(lblOn.getWidth(),lblOn.getHeight(),Image.SCALE_SMOOTH);
        lblOn.setIcon(new ImageIcon(scale));
    }
    private void Close()
    {
        Icon i = lblOff.getIcon();
        ImageIcon icon = (ImageIcon)i;
        Image scale = icon.getImage().getScaledInstance(lblOff.getWidth(),lblOff.getHeight(),Image.SCALE_SMOOTH);
        lblOff.setIcon(new ImageIcon(scale));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblUsername = new javax.swing.JLabel();
        lblPassword = new javax.swing.JLabel();
        lblOn = new javax.swing.JLabel();
        lblOff = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        btnLogin = new javax.swing.JButton();
        txtPassword = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("LOGIN");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblUsername.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        lblUsername.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername.setText("Username :");
        jPanel1.add(lblUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 96, -1, -1));

        lblPassword.setFont(new java.awt.Font("Microsoft YaHei UI", 1, 18)); // NOI18N
        lblPassword.setForeground(new java.awt.Color(255, 255, 255));
        lblPassword.setText("Password :");
        jPanel1.add(lblPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(34, 156, -1, -1));

        lblOn.setIcon(new javax.swing.ImageIcon("C:\\project\\on.png")); // NOI18N
        lblOn.setText("jLabel1");
        lblOn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblOnMousePressed(evt);
            }
        });
        jPanel1.add(lblOn, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 25, 25));

        lblOff.setIcon(new javax.swing.ImageIcon("C:\\project\\off.png")); // NOI18N
        lblOff.setText("jLabel2");
        lblOff.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lblOffMousePressed(evt);
            }
        });
        jPanel1.add(lblOff, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 25, 25));

        txtUsername.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        jPanel1.add(txtUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 97, 200, 30));

        btnLogin.setFont(new java.awt.Font("Microsoft YaHei UI Light", 1, 14)); // NOI18N
        btnLogin.setText("LOGIN");
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel1.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, -1, -1));

        txtPassword.setFont(new java.awt.Font("Microsoft YaHei UI Light", 0, 14)); // NOI18N
        jPanel1.add(txtPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(156, 157, 200, 30));

        jLabel1.setFont(new java.awt.Font("U4 Brothers. 00193", 2, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("WELCOME");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 260, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 400, 280));

        lblImage.setIcon(new javax.swing.ImageIcon("C:\\project\\pc.jpg")); // NOI18N
        getContentPane().add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        String s1= txtUsername.getText();
        String s2= txtPassword.getText();
        
        try
        {
            con = DriverManager.getConnection(url,username,password);
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from staff where BINARY SUsername = '"+s1+"' and BINARY SPassword = '"+s2+"'");
            int count = 0;
            
            while(rs.next())
            {
                count++;
            }
            
            if(s1.equals("")|| s2.equals(""))
            {
                JOptionPane.showMessageDialog(null,"Enter Username and Password");
            }
            else if(count==1)
            {
                JOptionPane.showMessageDialog(null, "Login Successful");
                MainMenu mm = new MainMenu();
                this.setVisible(false);
                mm.setVisible(true);
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please Check Again");
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void lblOnMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOnMousePressed
        lblOff.setVisible(true);
        lblOn.setVisible(false);
        txtPassword.setEchoChar((char)0);
    }//GEN-LAST:event_lblOnMousePressed

    private void lblOffMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblOffMousePressed
        lblOn.setVisible(true);
        lblOff.setVisible(false);
        txtPassword.setEchoChar('*');
    }//GEN-LAST:event_lblOffMousePressed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblOff;
    private javax.swing.JLabel lblOn;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables
}