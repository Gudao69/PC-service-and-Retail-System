/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package PCService;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author msi
 */
public class TableCreate extends javax.swing.JFrame {

    int frameWidth = 700;
    
    public TableCreate() {
    initComponents();
    columns();
    this.setPreferredSize(new Dimension(frameWidth, 330));
    this.pack();
    panelFooter.setLayout(null);
    panelFooter.setBounds(0, 120, panelFooter.getWidth(), panelFooter.getHeight());
    getRootPane().setDefaultButton(btnCreateTable);
    }

    private void clear()
    {
        txtTableName.setText(null);
        txtC1.setText(null);
        txtC2.setText(null);
        txtC3.setText(null);
        txtC4.setText(null);
        txtC5.setText(null);
        txtC6.setText(null);
        txtC7.setText(null);
        txtC8.setText(null);
        txtC9.setText(null);
        cboC1.setSelectedIndex(0);
        cboC2.setSelectedIndex(0);
        cboC3.setSelectedIndex(0);
        cboC4.setSelectedIndex(0);
        cboC5.setSelectedIndex(0);
        cboC6.setSelectedIndex(0);
        cboC7.setSelectedIndex(0);
        cboC8.setSelectedIndex(0);
        cboC9.setSelectedIndex(0);
        chkCPK1.setSelected(false);
        chkCPK2.setSelected(false);
        chkCPK3.setSelected(false);
        chkCPK4.setSelected(false);
        chkCPK5.setSelected(false);
        chkCPK6.setSelected(false);
        chkCPK7.setSelected(false);
        chkCPK8.setSelected(false);
        chkCPK9.setSelected(false);
    }
    
    private void columns()
    {
        for (int i = 3; i <=9; i++) 
        {
            getPanel(i).setVisible(false);
        }
    }
    
    public JPanel getPanel(int index) {
    int maxIndex = 9; // Set the maximum index value
    int panelIndex = maxIndex - index + 1; // Calculate the panel index based on the maximum index

    switch (panelIndex) {
        case 1:
            return jPanel9;
        case 2:
            return jPanel8;
        case 3:
            return jPanel7;
        case 4:
            return jPanel6;
        case 5:
            return jPanel5;
        case 6:
            return jPanel4;
        case 7:
            return jPanel3;
        case 8:
            return jPanel2;
        default:
            return null; // Return appropriate default panel or handle the case differently
    }
    }
    
    public JCheckBox getPCheckBox(int index) {
    int maxIndex = 9; // Set the maximum index value
    int panelIndex = maxIndex - index + 1; // Calculate the panel index based on the maximum index

    switch (panelIndex) {
        case 1:
            return chkCPK9;
        case 2:
            return chkCPK8;
        case 3:
            return chkCPK7;
        case 4:
            return chkCPK6;
        case 5:
            return chkCPK5;
        case 6:
            return chkCPK4;
        case 7:
            return chkCPK3;
        case 8:
            return chkCPK2;
        case 9:
            return chkCPK1;
        default:
            return null; // Return appropriate default panel or handle the case differently
    }
    }
    
private String[] getData(int count) 
{
    String[] data = new String[4];
    JTextField textField = null;
    JComboBox<String> comboBox = null;
    JCheckBox checkBoxNull = null;
    JCheckBox checkBoxPK = null;

    switch (count) 
    {
        case 1:
            textField = txtC1;
            comboBox = cboC1;
            checkBoxNull = chkCNull1;
            checkBoxPK = chkCPK1;
            break;
        case 2:
            textField = txtC2;
            comboBox = cboC2;
            checkBoxNull = chkCNull2;
            checkBoxPK = chkCPK2;
            break;
        case 3:
            textField = txtC3;
            comboBox = cboC3;
            checkBoxNull = chkCNull3;
            checkBoxPK = chkCPK3;
            break;
        case 4:
            textField = txtC4;
            comboBox = cboC4;
            checkBoxNull = chkCNull4;
            checkBoxPK = chkCPK4;
            break;
        case 5:
            textField = txtC5;
            comboBox = cboC5;
            checkBoxNull = chkCNull5;
            checkBoxPK = chkCPK5;
            break;
        case 6:
            textField = txtC6;
            comboBox = cboC6;
            checkBoxNull = chkCNull6;
            checkBoxPK = chkCPK6;
            break;
        case 7:
            textField = txtC7;
            comboBox = cboC7;
            checkBoxNull = chkCNull7;
            checkBoxPK = chkCPK7;
            break;
        case 8:
            textField = txtC8;
            comboBox = cboC8;
            checkBoxNull = chkCNull8;
            checkBoxPK = chkCPK8;
            break;
        case 9:
            textField = txtC9;
            comboBox = cboC9;
            checkBoxNull = chkCNull9;
            checkBoxPK = chkCPK9;
            break;
    }

    if (textField != null && comboBox != null && checkBoxNull != null && checkBoxPK != null) {
        String cName = textField.getText();
        String type = comboBox.getSelectedItem().toString();
        String aNull = checkBoxNull.isSelected() ? "NOT NULL" : "NULL";
        String pKey = checkBoxPK.isSelected() ? "Primary Key" : " ";
        data[0] = cName;
        data[1] = type;
        data[2] = aNull;
        data[3] = pKey;
    }
    return data;
}

private String[] mergeData(String[] previousData, String[] currentData) {
    if (previousData == null) 
    {
        return currentData;
    } 
    else if (currentData == null) 
    {
        return previousData;
    }
    
    String[] result = new String[previousData.length + currentData.length];
    System.arraycopy(previousData, 0, result, 0, previousData.length);
    System.arraycopy(currentData, 0, result, previousData.length, currentData.length);
    return result;
}

private String[] getCombinedData(int count) {
    String[] data = null;
    for (int i = 1; i <= count; i++) 
    {
        String[] currentData = getData(i);
        if (currentData != null) 
        {
            data = mergeData(data, currentData);
        }
    }

    return data;
}

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelRight = new javax.swing.JPanel();
        txtTableName = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        panelHeader = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        btnCreateTable = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cboCNo = new javax.swing.JComboBox<>();
        lblBack = new javax.swing.JLabel();
        panelLeft = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        txtC1 = new javax.swing.JTextField();
        cboC1 = new javax.swing.JComboBox<>();
        chkCNull1 = new javax.swing.JCheckBox();
        chkCPK1 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        txtC2 = new javax.swing.JTextField();
        cboC2 = new javax.swing.JComboBox<>();
        chkCNull2 = new javax.swing.JCheckBox();
        chkCPK2 = new javax.swing.JCheckBox();
        jPanel3 = new javax.swing.JPanel();
        txtC3 = new javax.swing.JTextField();
        cboC3 = new javax.swing.JComboBox<>();
        chkCNull3 = new javax.swing.JCheckBox();
        chkCPK3 = new javax.swing.JCheckBox();
        jPanel4 = new javax.swing.JPanel();
        txtC4 = new javax.swing.JTextField();
        cboC4 = new javax.swing.JComboBox<>();
        chkCNull4 = new javax.swing.JCheckBox();
        chkCPK4 = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        txtC5 = new javax.swing.JTextField();
        cboC5 = new javax.swing.JComboBox<>();
        chkCNull5 = new javax.swing.JCheckBox();
        chkCPK5 = new javax.swing.JCheckBox();
        jPanel6 = new javax.swing.JPanel();
        txtC6 = new javax.swing.JTextField();
        cboC6 = new javax.swing.JComboBox<>();
        chkCNull6 = new javax.swing.JCheckBox();
        chkCPK6 = new javax.swing.JCheckBox();
        jPanel7 = new javax.swing.JPanel();
        txtC7 = new javax.swing.JTextField();
        cboC7 = new javax.swing.JComboBox<>();
        chkCNull7 = new javax.swing.JCheckBox();
        chkCPK7 = new javax.swing.JCheckBox();
        jPanel8 = new javax.swing.JPanel();
        txtC8 = new javax.swing.JTextField();
        cboC8 = new javax.swing.JComboBox<>();
        chkCNull8 = new javax.swing.JCheckBox();
        chkCPK8 = new javax.swing.JCheckBox();
        jPanel9 = new javax.swing.JPanel();
        txtC9 = new javax.swing.JTextField();
        cboC9 = new javax.swing.JComboBox<>();
        chkCNull9 = new javax.swing.JCheckBox();
        chkCPK9 = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        panelFooter = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("DATA DEFINING");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelRight.setBackground(new java.awt.Color(0, 0, 0));
        panelRight.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtTableName.setBackground(new java.awt.Color(0, 0, 0));
        txtTableName.setForeground(new java.awt.Color(255, 255, 255));
        panelRight.add(txtTableName, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 130, 30));

        jLabel4.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 28)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Table");
        panelRight.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, 30));

        jLabel1.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Name");
        panelRight.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, -1, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon("C:\\project\\paint.jpg")); // NOI18N
        panelRight.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 560));

        getContentPane().add(panelRight, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 110, 220, 620));

        panelHeader.setBackground(new java.awt.Color(0, 0, 0));
        panelHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Primary Key");
        panelHeader.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 80, -1, 30));

        btnCreateTable.setFont(new java.awt.Font("Lucida Bright", 1, 16)); // NOI18N
        btnCreateTable.setText("Create Table");
        btnCreateTable.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateTableActionPerformed(evt);
            }
        });
        panelHeader.add(btnCreateTable, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 140, 39));

        jLabel7.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Column Name");
        panelHeader.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 80, -1, 30));

        jLabel8.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Data Type");
        panelHeader.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, -1, 30));

        jLabel9.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 20)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Null");
        panelHeader.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, -1, 30));

        jLabel5.setFont(new java.awt.Font("Bodoni MT Condensed", 1, 28)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("No of Columns");
        panelHeader.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, -1, 30));

        cboCNo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2", "3", "4", "5", "6", "7", "8", "9" }));
        cboCNo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cboCNoItemStateChanged(evt);
            }
        });
        cboCNo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCNoActionPerformed(evt);
            }
        });
        panelHeader.add(cboCNo, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 26, 50, -1));

        lblBack.setIcon(new javax.swing.ImageIcon("C:\\project\\back.png")); // NOI18N
        lblBack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblBackMouseClicked(evt);
            }
        });
        panelHeader.add(lblBack, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, 40));

        getContentPane().add(panelHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 700, 110));

        panelLeft.setBackground(new java.awt.Color(0, 0, 0));
        panelLeft.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout panelLeftLayout = new javax.swing.GroupLayout(panelLeft);
        panelLeft.setLayout(panelLeftLayout);
        panelLeftLayout.setHorizontalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        panelLeftLayout.setVerticalGroup(
            panelLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 560, Short.MAX_VALUE)
        );

        getContentPane().add(panelLeft, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 50, 560));

        jPanel1.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull1.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull1.setText("NOT NULL");

        chkCPK1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK1.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK1.setText("PRIMARY KEY");
        chkCPK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(txtC1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull1)
                    .addComponent(chkCPK1))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 114, 415, 55));

        jPanel2.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull2.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull2.setText("NOT NULL");

        chkCPK2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK2.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK2.setText("PRIMARY KEY");
        chkCPK2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(txtC2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC2, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK2)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC2, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull2)
                    .addComponent(chkCPK2))
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 176, 415, 55));

        jPanel3.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull3.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull3.setText("NOT NULL");

        chkCPK3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK3.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK3.setText("PRIMARY KEY");
        chkCPK3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(txtC3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC3, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK3)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC3, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull3)
                    .addComponent(chkCPK3))
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 239, 415, 55));

        jPanel4.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC4.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull4.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull4.setText("NOT NULL");

        chkCPK4.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK4.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK4.setText("PRIMARY KEY");
        chkCPK4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtC4, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC4, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK4)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull4)
                    .addComponent(chkCPK4))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 301, 415, 55));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull5.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull5.setText("NOT NULL");

        chkCPK5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK5.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK5.setText("PRIMARY KEY");
        chkCPK5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(txtC5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC5, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK5)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC5, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull5)
                    .addComponent(chkCPK5))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 363, 415, 55));

        jPanel6.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC6.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull6.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull6.setText("NOT NULL");

        chkCPK6.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK6.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK6.setText("PRIMARY KEY");
        chkCPK6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK6ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(txtC6, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC6, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK6)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull6)
                    .addComponent(chkCPK6))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 426, 415, 55));

        jPanel7.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC7.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull7.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull7.setText("NOT NULL");

        chkCPK7.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK7.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK7.setText("PRIMARY KEY");
        chkCPK7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(txtC7, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC7, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK7)
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC7, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull7)
                    .addComponent(chkCPK7))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 489, 415, 55));

        jPanel8.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC8.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull8.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull8.setText("NOT NULL");

        chkCPK8.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK8.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK8.setText("PRIMARY KEY");
        chkCPK8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(txtC8, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC8, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK8)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC8, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull8)
                    .addComponent(chkCPK8))
                .addGap(14, 14, 14))
        );

        getContentPane().add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 552, 415, 55));

        jPanel9.setBackground(new java.awt.Color(0, 0, 0,150));

        cboC9.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "int", "char", "float", "date", "time", "double", "varchar(10)", "varchar(20)", "varchar(50)" }));

        chkCNull9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCNull9.setForeground(new java.awt.Color(255, 255, 255));
        chkCNull9.setText("NOT NULL");

        chkCPK9.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        chkCPK9.setForeground(new java.awt.Color(255, 255, 255));
        chkCPK9.setText("PRIMARY KEY");
        chkCPK9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chkCPK9ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(txtC9, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboC9, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chkCNull9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(chkCPK9)
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtC9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboC9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chkCNull9)
                    .addComponent(chkCPK9))
                .addGap(15, 15, 15))
        );

        getContentPane().add(jPanel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 614, 415, 55));

        jLabel2.setIcon(new javax.swing.ImageIcon("C:\\project\\season1.jpg")); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 430, 560));

        panelFooter.setBackground(new java.awt.Color(0, 0, 0));
        panelFooter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 670, 700, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboCNoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cboCNoItemStateChanged
        int CNo = Integer.parseInt(cboCNo.getSelectedItem().toString());
        int height=280;
        int footerY = 120;
        for (int i = 2; i <=CNo; i++) 
        {
            getPanel(i).setVisible(true);
        
            for (int j = i + 1; j <= 9; j++) 
            {
                getPanel(j).setVisible(false);
            } 
            height += 60;
            footerY += 60;
        }
        this.setPreferredSize(new Dimension(frameWidth, height));
        this.pack( );
        panelFooter.setBounds(0,footerY,panelFooter.getWidth(),panelFooter.getHeight());
    }//GEN-LAST:event_cboCNoItemStateChanged

    private void chkCPK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK1ActionPerformed
        if(chkCPK1.isSelected())
        {
            chkCNull1.setSelected(true);
            for (int i = 2; i <= 9; i++) 
            {
                getPCheckBox(i).setEnabled(false);
            }
        }
        else
        {
            chkCNull1.setSelected(false);
            for (int i = 2; i <= 9; i++) 
            {
                getPCheckBox(i).setEnabled(true);
            }
        }
    }//GEN-LAST:event_chkCPK1ActionPerformed

    private void chkCPK2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK2ActionPerformed
        if(chkCPK2.isSelected())
        {
            chkCNull2.setSelected(true);
            chkCPK1.setEnabled(false);
            chkCPK3.setEnabled(false);
            chkCPK4.setEnabled(false);
            chkCPK5.setEnabled(false);
            chkCPK6.setEnabled(false);
            chkCPK7.setEnabled(false);
            chkCPK8.setEnabled(false);
            chkCPK9.setEnabled(false);
        }
        else
        {
            chkCNull2.setSelected(false);
            chkCPK1.setEnabled(true);
            chkCPK3.setEnabled(true);
            chkCPK4.setEnabled(true);
            chkCPK5.setEnabled(true);
            chkCPK6.setEnabled(true);
            chkCPK7.setEnabled(true);
            chkCPK8.setEnabled(true);
            chkCPK9.setEnabled(true);
        }
    }//GEN-LAST:event_chkCPK2ActionPerformed

    private void chkCPK3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK3ActionPerformed
        if(chkCPK3.isSelected())
        {
            chkCNull3.setSelected(true);
            chkCPK1.setEnabled(false);
            chkCPK2.setEnabled(false);
            chkCPK4.setEnabled(false);
            chkCPK5.setEnabled(false);
            chkCPK6.setEnabled(false);
            chkCPK7.setEnabled(false);
            chkCPK8.setEnabled(false);
            chkCPK9.setEnabled(false);
        }
        else
        {
            chkCNull3.setSelected(false);
            chkCPK1.setEnabled(true);
            chkCPK2.setEnabled(true);
            chkCPK4.setEnabled(true);
            chkCPK5.setEnabled(true);
            chkCPK6.setEnabled(true);
            chkCPK7.setEnabled(true);
            chkCPK8.setEnabled(true);
            chkCPK9.setEnabled(true);
        }
    }//GEN-LAST:event_chkCPK3ActionPerformed

    private void chkCPK4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK4ActionPerformed
        if(chkCPK4.isSelected())
        {
            chkCNull4.setSelected(true);
            chkCPK1.setEnabled(false);
            chkCPK3.setEnabled(false);
            chkCPK2.setEnabled(false);
            chkCPK5.setEnabled(false);
            chkCPK6.setEnabled(false);
            chkCPK7.setEnabled(false);
            chkCPK8.setEnabled(false);
            chkCPK9.setEnabled(false);
        }
        else
        {
            chkCNull4.setSelected(false);
            chkCPK1.setEnabled(true);
            chkCPK3.setEnabled(true);
            chkCPK2.setEnabled(true);
            chkCPK5.setEnabled(true);
            chkCPK6.setEnabled(true);
            chkCPK7.setEnabled(true);
            chkCPK8.setEnabled(true);
            chkCPK9.setEnabled(true);
        }
    }//GEN-LAST:event_chkCPK4ActionPerformed

    private void chkCPK5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK5ActionPerformed
        if(chkCPK5.isSelected())
        {
            chkCNull5.setSelected(true);
            chkCPK1.setEnabled(false);
            chkCPK3.setEnabled(false);
            chkCPK4.setEnabled(false);
            chkCPK2.setEnabled(false);
            chkCPK6.setEnabled(false);
            chkCPK7.setEnabled(false);
            chkCPK8.setEnabled(false);
            chkCPK9.setEnabled(false);
        }
        else
        {
            chkCNull5.setSelected(false);
            chkCPK1.setEnabled(true);
            chkCPK3.setEnabled(true);
            chkCPK4.setEnabled(true);
            chkCPK2.setEnabled(true);
            chkCPK6.setEnabled(true);
            chkCPK7.setEnabled(true);
            chkCPK8.setEnabled(true);
            chkCPK9.setEnabled(true);
        }
    }//GEN-LAST:event_chkCPK5ActionPerformed

    private void chkCPK6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK6ActionPerformed
        if(chkCPK6.isSelected())
        {
            chkCNull6.setSelected(true);
            chkCPK1.setEnabled(false);
            chkCPK3.setEnabled(false);
            chkCPK4.setEnabled(false);
            chkCPK5.setEnabled(false);
            chkCPK2.setEnabled(false);
            chkCPK7.setEnabled(false);
            chkCPK8.setEnabled(false);
            chkCPK9.setEnabled(false);
        }
        else
        {
            chkCNull6.setSelected(false);
            chkCPK1.setEnabled(true);
            chkCPK3.setEnabled(true);
            chkCPK4.setEnabled(true);
            chkCPK5.setEnabled(true);
            chkCPK2.setEnabled(true);
            chkCPK7.setEnabled(true);
            chkCPK8.setEnabled(true);
            chkCPK9.setEnabled(true);
        }
    }//GEN-LAST:event_chkCPK6ActionPerformed

    private void chkCPK7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK7ActionPerformed
        if(chkCPK7.isSelected())
        {
            chkCNull7.setSelected(true);
            chkCPK1.setEnabled(false);
            chkCPK3.setEnabled(false);
            chkCPK4.setEnabled(false);
            chkCPK5.setEnabled(false);
            chkCPK6.setEnabled(false);
            chkCPK2.setEnabled(false);
            chkCPK8.setEnabled(false);
            chkCPK9.setEnabled(false);
        }
        else
        {
            chkCNull7.setSelected(false);
            chkCPK1.setEnabled(true);
            chkCPK3.setEnabled(true);
            chkCPK4.setEnabled(true);
            chkCPK5.setEnabled(true);
            chkCPK6.setEnabled(true);
            chkCPK2.setEnabled(true);
            chkCPK8.setEnabled(true);
            chkCPK9.setEnabled(true);
        }
    }//GEN-LAST:event_chkCPK7ActionPerformed

    private void chkCPK8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK8ActionPerformed
       if(chkCPK8.isSelected())
        {
            chkCNull8.setSelected(true);
            chkCPK1.setEnabled(false);
            chkCPK3.setEnabled(false);
            chkCPK4.setEnabled(false);
            chkCPK5.setEnabled(false);
            chkCPK6.setEnabled(false);
            chkCPK7.setEnabled(false);
            chkCPK2.setEnabled(false);
            chkCPK9.setEnabled(false);
        }
        else
        {
            chkCNull8.setSelected(false);
            chkCPK1.setEnabled(true);
            chkCPK3.setEnabled(true);
            chkCPK4.setEnabled(true);
            chkCPK5.setEnabled(true);
            chkCPK6.setEnabled(true);
            chkCPK7.setEnabled(true);
            chkCPK8.setEnabled(true);
            chkCPK9.setEnabled(true);
        }
    }//GEN-LAST:event_chkCPK8ActionPerformed

    private void chkCPK9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chkCPK9ActionPerformed
        if(chkCPK9.isSelected())
        {
            chkCNull9.setSelected(true);
            chkCPK1.setEnabled(false);
            chkCPK3.setEnabled(false);
            chkCPK4.setEnabled(false);
            chkCPK5.setEnabled(false);
            chkCPK6.setEnabled(false);
            chkCPK7.setEnabled(false);
            chkCPK8.setEnabled(false);
            chkCPK2.setEnabled(false);
        }
        else
        {
            chkCNull9.setSelected(false);
            chkCPK1.setEnabled(true);
            chkCPK3.setEnabled(true);
            chkCPK4.setEnabled(true);
            chkCPK5.setEnabled(true);
            chkCPK6.setEnabled(true);
            chkCPK7.setEnabled(true);
            chkCPK8.setEnabled(true);
            chkCPK2.setEnabled(true);
        }
    }//GEN-LAST:event_chkCPK9ActionPerformed

    private void CreateTable()
    {
        if (!txtTableName.getText().equals("")) 
        {
            int CNo = Integer.parseInt(cboCNo.getSelectedItem().toString());
            String[] allData = getCombinedData(CNo);
            String tName = txtTableName.getText();
            try 
            {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/pcs", "root", "Ehedenandayo");
                StringBuilder sql = new StringBuilder("CREATE TABLE pcs."+ tName +" (");

                for (int i = 0; i < allData.length; i += 4) 
                {
                    String cName = allData[i];
                    String type = allData[i + 1];
                    String aNull = allData[i + 2];
                    String pKey = allData[i + 3];

                    sql.append(" "+" ").append(cName).append(" "+" ").append(type).append(" "+" ")
                    .append(aNull).append(" "+" ").append(pKey).append(",");
                }
                // Remove the trailing comma if there are columns defined
                if (allData.length > 0) 
                {
                    sql.setLength(sql.length() - 1);
                }
                sql.append(")");

                String finalSql = sql.toString();
                PreparedStatement pstmt = con.prepareStatement(finalSql);
                pstmt.execute();
                JOptionPane.showMessageDialog(null, "Table Created");
                clear();
                } 
            catch (Exception e) 
            {
                JOptionPane.showMessageDialog(null, e);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Please Fill the Table Name");
        }
    }
    
    private void btnCreateTableActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateTableActionPerformed
        String number = cboCNo.getSelectedItem().toString();
        int columnCount = Integer.parseInt(number);

        boolean isFieldsEmpty = false;
        for (int i = 1; i <= columnCount; i++) 
        {
            JTextField textField = getColumnTextField(i);
            if (textField.getText().isEmpty()) 
            {
                isFieldsEmpty = true;
                break;
            }
        }

        if (isFieldsEmpty) 
        {
            JOptionPane.showMessageDialog(null, "Please Fill the Column Names!!!");
        } 
        else 
        {
            CreateTable();
        }
    }//GEN-LAST:event_btnCreateTableActionPerformed

    private JTextField getColumnTextField(int columnIndex) 
    {
    switch (columnIndex) 
    {
        case 1:
            return txtC1;
        case 2:
            return txtC2;
        case 3:
            return txtC3;
        case 4:
            return txtC4;
        case 5:
            return txtC5;
        case 6:
            return txtC6;
        case 7:
            return txtC7;
        case 8:
            return txtC8;
        case 9:
            return txtC9;
        default:
            throw new IllegalArgumentException("Invalid column index: " + columnIndex);
    }
}
    
    private void cboCNoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCNoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCNoActionPerformed

    private void lblBackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblBackMouseClicked
        int dialog = JOptionPane.showConfirmDialog(null, "Are You Sure You Want To EXIT?","Warning",JOptionPane.YES_NO_OPTION);
        if(dialog==JOptionPane.YES_OPTION)
        {
            MainMenu mm = new MainMenu();
            this.setVisible(false);
            mm.setVisible(true);
        }
    }//GEN-LAST:event_lblBackMouseClicked

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
            java.util.logging.Logger.getLogger(TableCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TableCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TableCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TableCreate.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TableCreate().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateTable;
    private javax.swing.JComboBox<String> cboC1;
    private javax.swing.JComboBox<String> cboC2;
    private javax.swing.JComboBox<String> cboC3;
    private javax.swing.JComboBox<String> cboC4;
    private javax.swing.JComboBox<String> cboC5;
    private javax.swing.JComboBox<String> cboC6;
    private javax.swing.JComboBox<String> cboC7;
    private javax.swing.JComboBox<String> cboC8;
    private javax.swing.JComboBox<String> cboC9;
    private javax.swing.JComboBox<String> cboCNo;
    private javax.swing.JCheckBox chkCNull1;
    private javax.swing.JCheckBox chkCNull2;
    private javax.swing.JCheckBox chkCNull3;
    private javax.swing.JCheckBox chkCNull4;
    private javax.swing.JCheckBox chkCNull5;
    private javax.swing.JCheckBox chkCNull6;
    private javax.swing.JCheckBox chkCNull7;
    private javax.swing.JCheckBox chkCNull8;
    private javax.swing.JCheckBox chkCNull9;
    private javax.swing.JCheckBox chkCPK1;
    private javax.swing.JCheckBox chkCPK2;
    private javax.swing.JCheckBox chkCPK3;
    private javax.swing.JCheckBox chkCPK4;
    private javax.swing.JCheckBox chkCPK5;
    private javax.swing.JCheckBox chkCPK6;
    private javax.swing.JCheckBox chkCPK7;
    private javax.swing.JCheckBox chkCPK8;
    private javax.swing.JCheckBox chkCPK9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JLabel lblBack;
    private javax.swing.JPanel panelFooter;
    private javax.swing.JPanel panelHeader;
    private javax.swing.JPanel panelLeft;
    private javax.swing.JPanel panelRight;
    private javax.swing.JTextField txtC1;
    private javax.swing.JTextField txtC2;
    private javax.swing.JTextField txtC3;
    private javax.swing.JTextField txtC4;
    private javax.swing.JTextField txtC5;
    private javax.swing.JTextField txtC6;
    private javax.swing.JTextField txtC7;
    private javax.swing.JTextField txtC8;
    private javax.swing.JTextField txtC9;
    private javax.swing.JTextField txtTableName;
    // End of variables declaration//GEN-END:variables
}
