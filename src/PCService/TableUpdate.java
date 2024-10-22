/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PCService;
import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.DatabaseMetaData;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import java.util.List;
import java.util.ArrayList;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TableUpdate extends javax.swing.JFrame {

    Statement stmt = null;
    String url = "jdbc:mysql://localhost:3306/pcs";
    String driver = "com.mysql.cj.jdbc.Driver";
    String username = "root";
    String password = "Ehedenandayo";
    Connection con = null;
    String oldName = null;
    int TNameIndex;
    DefaultTableModel m = new DefaultTableModel();
    
    public TableUpdate() {
        initComponents();
        this.setPreferredSize(new Dimension(890, 560));
        this.pack();
        AddComboItem();
        jPanel2.setVisible(true);
        jPanel1.setVisible(false);
        txtSearch.getDocument().addDocumentListener(documentListener);
        txtSearch.setText("Search Anything you want here!!");
        
        txtSearch.addFocusListener(new FocusAdapter() {
    @Override
    public void focusGained(FocusEvent e) {
        if (txtSearch.getText().equals("Search Anything you want here!!")) {
            txtSearch.setText(""); // Clear the placeholder text when the text field is focused
            txtSearch.setForeground(Color.BLACK); // Set the text color to black
        }
    }
    
    @Override
    public void focusLost(FocusEvent e) {
        if (txtSearch.getText().isEmpty()) {
            txtSearch.setForeground(Color.GRAY); // Set the text color back to gray
            txtSearch.setText("Search Anything you want here!!"); // Restore the placeholder text if no input is entered
            TableData();
        }
    }
});

    }

    
    DocumentListener documentListener = new DocumentListener() {
    @Override
    public void insertUpdate(DocumentEvent e) {
        // This method is called when text is inserted into the document
        search();
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        // This method is called when text is removed from the document
         search();
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        // This method is called when the attributes of the document change
         search();
    }
};
    
    private void search() {
    String tableName = (String) cboTableName.getSelectedItem();
    String searchText = txtSearch.getText();
    m = (DefaultTableModel)T1.getModel();

    try {
        Class.forName(driver);
        con = DriverManager.getConnection(url,username,password);
        Statement statement = con.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE 1 = 0");
        
        // Clear the existing table data
        m.setRowCount(0);
        
        // Retrieve column names
        ResultSetMetaData metaData = (ResultSetMetaData) resultSet.getMetaData();
        int columnCount = metaData.getColumnCount();
        String[] columnNames = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) 
        {
            columnNames[i - 1] = metaData.getColumnName(i);
        }
        m.setColumnIdentifiers(columnNames);
        
        // Construct the SQL query with dynamic column names
        StringBuilder query = new StringBuilder();
        query.append("SELECT * FROM ").append(tableName).append(" WHERE ");
        for (int i = 1; i <= columnCount; i++) 
        {
            query.append(metaData.getColumnName(i)).append(" LIKE '%").append(searchText).append("%'");
            if (i < columnCount) 
            {
                query.append(" OR ");
            }
        }
        // Execute the SQL query with dynamic column names
        resultSet = statement.executeQuery(query.toString());
        // Populate the table with the retrieved data
        while (resultSet.next()) 
        {
            Object[] rowData = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) 
            {
                rowData[i - 1] = resultSet.getObject(i);
            }
            m.addRow(rowData);
        }
    } 
    catch (Exception e) 
    {
        e.printStackTrace();
    }
}
    
    private void getCAutoID() 
    {
    try 
    {
        con = DriverManager.getConnection(url, username, password);
        String query = "SELECT MAX(ID) FROM customer";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        m = (DefaultTableModel)T1.getModel();
        int maxrow = m.getRowCount();
        String maxId = null;
        if (rs.next()) 
        {
            maxId = rs.getString(1);
        }

        if (maxId == null ||maxId.isEmpty()) 
        {
            m.setValueAt("C0001", maxrow-1 , 0); // Adjust the row and column index
        } 
        else 
        {
            int intval = Integer.parseInt(maxId.substring(1));
            intval++;
            m.setValueAt(String.format("C%04d", intval), maxrow-1 , 0); // Adjust the row and column index
        }
    } 
    catch (Exception ex) 
    {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}
    
    private void getPAutoID() 
    {
    try 
    {
        con = DriverManager.getConnection(url, username, password);
        String query = "SELECT MAX(ID) FROM products";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        m = (DefaultTableModel)T1.getModel();
        int maxrow = m.getRowCount();
        String maxId = null;
        if (rs.next()) 
        {
            maxId = rs.getString(1);
        }

        if (maxId == null ||maxId.isEmpty()) 
        {
            m.setValueAt("P0001", maxrow-1 , 0); // Adjust the row and column index
        } 
        else 
        {
            int intval = Integer.parseInt(maxId.substring(1));
            intval++;
            m.setValueAt(String.format("P%04d", intval), maxrow-1 , 0); // Adjust the row and column index
        }
    } 
    catch (Exception ex) 
    {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}
    
    private void getSAutoID() 
    {
    try 
    {
        con = DriverManager.getConnection(url, username, password);
        String query = "SELECT MAX(SID) FROM staff";
        PreparedStatement pstmt = con.prepareStatement(query);
        ResultSet rs = pstmt.executeQuery();
        m = (DefaultTableModel)T1.getModel();
        int maxrow = m.getRowCount();
        String maxId = null;
        if (rs.next()) 
        {
            maxId = rs.getString(1);
        }

        if (maxId == null ||maxId.isEmpty()) 
        {
            m.setValueAt("S001", maxrow-1 , 0); // Adjust the row and column index
        } 
        else 
        {
            int intval = Integer.parseInt(maxId.substring(1));
            intval++;
            m.setValueAt(String.format("S%03d", intval), maxrow-1 , 0); // Adjust the row and column index
        }
    } 
    catch (Exception ex) 
    {
        JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}
    
    private void AddComboItem()
    {
        try
        {
            ResultSet rs=null;
            Class.forName(driver);
            con=DriverManager.getConnection(url,username,password);
            DatabaseMetaData dbmd = con.getMetaData();
            String[]type = {"TABLE"};
            rs = dbmd.getTables("pcs",null,"%",type);
            while(rs.next())
            {
                cboTableName.addItem(rs.getString("TABLE_NAME"));
                //TableData();
            }
        }
        catch(Exception e)
        {
            
        }
    }
    
    private void TableDef()
    {
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            String sql ="describe pcs."+cboTableName.getSelectedItem()+"";
            PreparedStatement pstmt = con.prepareStatement (sql);
            ResultSet RS= pstmt.executeQuery();
            T1.setModel(DbUtils.resultSetToTableModel(RS));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,e);
        }
    }
    
    private void TableData()
    {
        try
        {
            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            String sql= "select * from pcs."+cboTableName.getSelectedItem()+"";
            PreparedStatement pstmt = con.prepareStatement (sql);
            ResultSet RS= pstmt.executeQuery();
            T1.setModel(DbUtils.resultSetToTableModel(RS));
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    
    private void Clear()
    {
        txtCName.setText(null);
       cboType.setSelectedIndex(0);
       chkNull.setSelected(false);
    }
    
    private Object[] rowdata()
    {
        m = (DefaultTableModel)T1.getModel();
        int columnCount = m.getColumnCount();
        Object[] rowdata = new Object[columnCount];

        for(int i = 0;i < columnCount; i++){
            rowdata[i] = T1.getValueAt(T1.getSelectedRow(),i);

        }
        return rowdata;
    }
    
    private void AddData(Object[] RowData)
    {
        try{

            Class.forName(driver);
            con = DriverManager.getConnection(url,username,password);
            String sql = "INSERT INTO pcs."+ cboTableName.getSelectedItem().toString() +" VALUES (";

            for (int i = 0; i < RowData.length; i++) 
            {
                if (RowData[i] instanceof Integer) 
                {
                    sql += RowData[i];
                } 
                else 
                {
                    sql += "'" + RowData[i] + "'";
                }

                if (i < RowData.length - 1) 
                {
                     sql += ", ";
                }
             }
                sql += ")";
               Statement  stat = con.createStatement();
               stat.executeUpdate(sql);
               JOptionPane.showMessageDialog(null,"Record inserted successfully");
            } 
        catch (Exception e) 
        {
              JOptionPane.showMessageDialog(null, e);
         }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSearch = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cboTableCommands = new javax.swing.JComboBox<>();
        cboTableName = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnDAdd = new javax.swing.JButton();
        btnDUpdate = new javax.swing.JButton();
        btnDDelete = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        btnDeleteC = new javax.swing.JButton();
        btnAddC = new javax.swing.JButton();
        btnUpdateC = new javax.swing.JButton();
        txtCName = new javax.swing.JTextField();
        cboType = new javax.swing.JComboBox<>();
        chkNull = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        T1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        btnDeleteT = new javax.swing.JButton();
        btnRAdd = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DATA MANIPULATION");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtSearch.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        txtSearch.setText("Search Anything you want here!!");
        txtSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchActionPerformed(evt);
            }
        });
        getContentPane().add(txtSearch, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, 230, 30));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0,200));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(255, 255, 255));
        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("TABLE COMMANDS");
        jPanel3.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 240, 40));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 20, 250, 40));

        cboTableCommands.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Data", "Definition" }));
        cboTableCommands.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTableCommandsItemStateChanged(evt);
            }
        });
        cboTableCommands.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTableCommandsActionPerformed(evt);
            }
        });
        getContentPane().add(cboTableCommands, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 80, 120, 30));

        cboTableName.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        cboTableName.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboTableNameItemStateChanged(evt);
            }
        });
        cboTableName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTableNameActionPerformed(evt);
            }
        });
        getContentPane().add(cboTableName, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 170, 30));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0,200));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDAdd.setBackground(new java.awt.Color(255, 0, 0));
        btnDAdd.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnDAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnDAdd.setText("ADD DATA");
        btnDAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDAddActionPerformed(evt);
            }
        });
        jPanel2.add(btnDAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 40, 170, 40));

        btnDUpdate.setBackground(new java.awt.Color(255, 0, 0));
        btnDUpdate.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnDUpdate.setForeground(new java.awt.Color(255, 255, 255));
        btnDUpdate.setText("UPDATE DATA");
        btnDUpdate.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDUpdateActionPerformed(evt);
            }
        });
        jPanel2.add(btnDUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 120, 170, 40));

        btnDDelete.setBackground(new java.awt.Color(255, 0, 0));
        btnDDelete.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnDDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDDelete.setText("DELETE DATA");
        btnDDelete.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        btnDDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDDeleteActionPerformed(evt);
            }
        });
        jPanel2.add(btnDDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 210, 170, 40));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 150, 310));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0,200));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnDeleteC.setBackground(new java.awt.Color(255, 0, 0));
        btnDeleteC.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        btnDeleteC.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteC.setText("DELETE COLUMN");
        btnDeleteC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteCActionPerformed(evt);
            }
        });
        jPanel1.add(btnDeleteC, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 230, 170, 40));

        btnAddC.setBackground(new java.awt.Color(255, 0, 0));
        btnAddC.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        btnAddC.setForeground(new java.awt.Color(255, 255, 255));
        btnAddC.setText("ADD COLUMN");
        btnAddC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddCActionPerformed(evt);
            }
        });
        jPanel1.add(btnAddC, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 120, 170, 40));

        btnUpdateC.setBackground(new java.awt.Color(255, 0, 0));
        btnUpdateC.setFont(new java.awt.Font("Comic Sans MS", 1, 13)); // NOI18N
        btnUpdateC.setForeground(new java.awt.Color(255, 255, 255));
        btnUpdateC.setText("UPDATE COLUMN");
        btnUpdateC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateCActionPerformed(evt);
            }
        });
        jPanel1.add(btnUpdateC, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 174, 170, 40));

        txtCName.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jPanel1.add(txtCName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 130, 30));

        cboType.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        cboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));
        jPanel1.add(cboType, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 130, -1));

        chkNull.setBackground(new java.awt.Color(0, 0, 0,150));
        chkNull.setFont(new java.awt.Font("Comic Sans MS", 1, 14)); // NOI18N
        chkNull.setForeground(new java.awt.Color(255, 255, 255));
        chkNull.setText("NOT NULL");
        chkNull.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkNullActionPerformed(evt);
            }
        });
        jPanel1.add(chkNull, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 110, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 150, 310));

        T1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        T1.setFont(new java.awt.Font("Comic Sans MS", 0, 14)); // NOI18N
        T1.setForeground(new java.awt.Color(204, 0, 0));
        T1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        T1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                T1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(T1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 730, 310));

        jButton5.setBackground(new java.awt.Color(255, 0, 0));
        jButton5.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("BACK");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 490, 70, -1));

        btnDeleteT.setBackground(new java.awt.Color(255, 0, 0));
        btnDeleteT.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnDeleteT.setForeground(new java.awt.Color(255, 255, 255));
        btnDeleteT.setText("DELETE TABLE");
        btnDeleteT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteTActionPerformed(evt);
            }
        });
        getContentPane().add(btnDeleteT, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 450, -1, -1));

        btnRAdd.setBackground(new java.awt.Color(255, 0, 0));
        btnRAdd.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        btnRAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnRAdd.setText("ADD NEW ROW");
        btnRAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRAddActionPerformed(evt);
            }
        });
        getContentPane().add(btnRAdd, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 450, -1, -1));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0,200));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("TABLE NAMES");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 5, 180, 30));

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 180, 40));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\project\\akatsuki.png")); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 880, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchActionPerformed

    }//GEN-LAST:event_txtSearchActionPerformed

    private void cboTableCommandsItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTableCommandsItemStateChanged
        String Command = cboTableCommands.getSelectedItem().toString();
        if( Command.equals("Data"))
        {
            btnRAdd.setVisible(true);
            jPanel1.setVisible(false);
            jPanel2.setVisible(true);
            txtSearch.setText("Search Anything you want here!!");
            txtSearch.setVisible(true);
            TableData();
            isNewRowAdded=false;
        }
        else if(Command.equals("Definition"))
        {
            btnRAdd.setVisible(false);
            jPanel1.setVisible(true);
            jPanel2.setVisible(false);
            txtSearch.setVisible(false);
            TableDef();
        }
    }//GEN-LAST:event_cboTableCommandsItemStateChanged

    private void cboTableCommandsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTableCommandsActionPerformed
        
    }//GEN-LAST:event_cboTableCommandsActionPerformed

    private void cboTableNameItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboTableNameItemStateChanged
        String Command = cboTableCommands.getSelectedItem().toString();
        TNameIndex= cboTableName.getSelectedIndex();
        if(Command.equals("Data"))
        {
            TableData();
            isNewRowAdded=false;
        }
        else if(Command.equals("Definition"))
        {
            TableDef();
        }
    }//GEN-LAST:event_cboTableNameItemStateChanged

    private void cboTableNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTableNameActionPerformed
        
    }//GEN-LAST:event_cboTableNameActionPerformed

    private void btnDAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDAddActionPerformed
        if(T1.getSelectedRow()!=-1)
        {
            Object add[]= rowdata();
            AddData(add);
            TableData();
            isNewRowAdded=false;
        }
        else
        {
            JOptionPane.showMessageDialog(null,"Please kindly reselect the row!!");
        }
    }//GEN-LAST:event_btnDAddActionPerformed

    private void btnDUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDUpdateActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To UPDATE?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialog==JOptionPane.YES_OPTION)
        {
            if(T1.getSelectedRow()!=-1)
            {
                try
                {
                    Class.forName(driver);
                    con = DriverManager.getConnection(url, username, password);
                    m = (DefaultTableModel) T1.getModel();
                    int SelectedRow = T1.getSelectedRow();
                    // Retrieve column names and values for the selected row
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 0; i < m.getColumnCount(); i++)
                    {
                        String columnName = m.getColumnName(i);
                        columnNames.add(columnName);
                    }

                    List<Object> newValues = new ArrayList<>();
                    for (int i = 0; i < columnNames.size(); i++)
                    {
                        Object newValue = m.getValueAt(SelectedRow, i);
                        newValues.add(newValue);
                    }
                    // Retrieve the ID column name, table name, and selected ID
                    String idColumnName = m.getColumnName(0);
                    String tableName = "pcs." + cboTableName.getSelectedItem().toString();
                    String selectedID = m.getValueAt(SelectedRow, 0).toString();
                    // Build the SQL UPDATE statement
                    StringBuilder sqlBuilder = new StringBuilder("UPDATE ")
                    .append(tableName).append(" SET ");

                    for (int i = 0; i < columnNames.size(); i++)
                    {
                        String columnName = columnNames.get(i);
                        Object newValue = newValues.get(i);

                        sqlBuilder.append(columnName)
                        .append(" = ");

                        if (newValue instanceof Integer)
                        {
                            sqlBuilder.append(newValue);
                        }
                        else
                        {
                            sqlBuilder.append("'").append(newValue).append("'");
                        }

                        if (i < columnNames.size() - 1)
                        {
                            sqlBuilder.append(", ");
                        }
                    }

                    sqlBuilder.append(" WHERE ").append(idColumnName).append(" = ");

                    try
                    {
                        int idValue = Integer.parseInt(selectedID);
                        sqlBuilder.append(idValue);
                    }
                    catch (NumberFormatException e)
                    {
                        sqlBuilder.append("'").append(selectedID.replace("'", "''")).append("'");
                    }

                    String sql = sqlBuilder.toString();
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Updated successfully");
                    TableData();

                }
                catch (Exception e)
                {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
            else
            {
                JOptionPane.showMessageDialog(null,"Please kindly reselect the row!!");
            }
        }
    }//GEN-LAST:event_btnDUpdateActionPerformed

    private void btnDDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDDeleteActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To DELETE?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialog==JOptionPane.YES_OPTION)
        {
            try
            {
                m = (DefaultTableModel)T1.getModel();
                String UniqueColumn = m.getColumnName(0);
                int SelectedRow = T1.getSelectedRow();
                if(SelectedRow >= 0)
                {
                    String value = m.getValueAt(SelectedRow, 0).toString();
                    Class.forName(driver);
                    con = DriverManager.getConnection(url,username,password);
                    String sql = "delete from pcs."+cboTableName.getSelectedItem().toString()+" where "+UniqueColumn+" = '"+value+"' ";
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null,"Deleted Successfully");
                    TableData();
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"Please Select a Row and Try Again");
                }
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        if(dialog==JOptionPane.NO_OPTION)
        {
            return;
        }
    }//GEN-LAST:event_btnDDeleteActionPerformed

    private void btnDeleteCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteCActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To DELETE?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialog==JOptionPane.YES_OPTION)
        {
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(url,username,password);
                String sql = "alter table pcs."+cboTableName.getSelectedItem()+" DROP COLUMN "+oldName+" ";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.execute();
                TableDef();
                JOptionPane.showMessageDialog(this, "Column Deleted");
                Clear();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_btnDeleteCActionPerformed

    private void btnAddCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddCActionPerformed
        String cName = txtCName.getText();
        String type = cboType.getSelectedItem().toString();
        String st;
        if(chkNull.isSelected()==true)
        {
            st = "NOT NULL";
        }
        else
        {
            st = "NULL";
        }
        if(cName.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Please Fill the Column Name!!");
        }
        else
        {
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(url,username,password);
                String sql = "alter table pcs. "+cboTableName.getSelectedItem()+" add "+cName+" "+type+" "+st+" ";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.execute();
                JOptionPane.showMessageDialog(this, "Column Added Successfully");
                TableDef();
                Clear();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        
    }//GEN-LAST:event_btnAddCActionPerformed

    private void btnUpdateCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateCActionPerformed
        int dialog = JOptionPane.YES_NO_OPTION;
        JOptionPane.showConfirmDialog(null, "Are You Sure You Want To Update?");
        if(dialog ==JOptionPane.YES_OPTION)
        {
            String newName = txtCName.getText();
            String type = cboType.getSelectedItem().toString();
            String st;
            if(chkNull.isSelected()==true)
            {
                st = "NOT NULL";
            }
            else
            {
                st = "NULL";
            }
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(url,username,password);
                String sql = "alter table pcs."+cboTableName.getSelectedItem()+" CHANGE COLUMN "+oldName+" "+ newName+" "+ type+" "+ st+"";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.execute();
                JOptionPane.showMessageDialog(this, "Column Updated");
                TableDef();
                Clear();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
        if(dialog==JOptionPane.NO_OPTION)
        {

        }
    }//GEN-LAST:event_btnUpdateCActionPerformed

    private void chkNullActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkNullActionPerformed

    }//GEN-LAST:event_chkNullActionPerformed

    private void T1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_T1MouseClicked
         m = (DefaultTableModel)T1.getModel();
        int index = T1.getSelectedRow();
        txtCName.setText(m.getValueAt(index,0).toString());
        cboType.setSelectedItem(m.getValueAt(index,1).toString());
        if(m.getValueAt(index,2).toString() .equals("YES"))
        {
            chkNull.setSelected(false);
        }
        else
        {
            chkNull.setSelected(true);
        }
        oldName = txtCName.getText();
    }//GEN-LAST:event_T1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        MainMenu mm = new MainMenu();
        this.setVisible(false);
        mm.setVisible(true);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnDeleteTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteTActionPerformed
        int dialog = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To DELETE?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialog==JOptionPane.YES_OPTION)
        {
            try
            {
                Class.forName(driver);
                con = DriverManager.getConnection(url,username,password);
                String sql = "drop table pcs."+cboTableName.getSelectedItem()+" ";
                PreparedStatement pstmt = con.prepareStatement(sql);
                pstmt.execute();
                JOptionPane.showMessageDialog(this, "Table Deleted");
                cboTableName.removeItemAt(TNameIndex);
                Clear();
            }
            catch(Exception e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
    }//GEN-LAST:event_btnDeleteTActionPerformed

    private boolean isNewRowAdded = false;

    private void btnRAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRAddActionPerformed
        if (!isNewRowAdded) 
        {
        m = (DefaultTableModel) T1.getModel();
        m.addRow(new Object[m.getColumnCount()]);   
            if(cboTableName.getSelectedItem().equals("customer"))
            {
                getCAutoID();
            }
            else if(cboTableName.getSelectedItem().equals("products"))
            {
                getPAutoID();
            }
            else if(cboTableName.getSelectedItem().equals("staff"))
            {
                getSAutoID();
            }
            else
            {
                
            }
            isNewRowAdded = true;
        }
    }//GEN-LAST:event_btnRAddActionPerformed

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
            java.util.logging.Logger.getLogger(TableUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableUpdate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableUpdate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable T1;
    private javax.swing.JButton btnAddC;
    private javax.swing.JButton btnDAdd;
    private javax.swing.JButton btnDDelete;
    private javax.swing.JButton btnDUpdate;
    private javax.swing.JButton btnDeleteC;
    private javax.swing.JButton btnDeleteT;
    private javax.swing.JButton btnRAdd;
    private javax.swing.JButton btnUpdateC;
    private javax.swing.JComboBox<String> cboTableCommands;
    private javax.swing.JComboBox<String> cboTableName;
    private javax.swing.JComboBox<String> cboType;
    private javax.swing.JCheckBox chkNull;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtCName;
    private javax.swing.JTextField txtSearch;
    // End of variables declaration//GEN-END:variables
}
