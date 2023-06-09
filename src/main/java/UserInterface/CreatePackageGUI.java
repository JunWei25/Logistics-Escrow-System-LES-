/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package UserInterface;

import Class.Parcel;
import Class.User;
import Utils.Utils;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author JUN WEI
 */
public class CreatePackageGUI extends javax.swing.JFrame {
      User currentUser;
    /**
     * Creates new form CreatePackageGUI
     */
      public CreatePackageGUI() {
        initComponents();
        refresh();
        initialisedDocumentListener();
    }
      
    public CreatePackageGUI(User currentUser) {
        initComponents();
        refresh();
        initialisedDocumentListener();
        this.currentUser = currentUser;
//        initComponents();
//        refresh();
//        initialisedDocumentListener();
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        receipientContactTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        receipientNameTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        packageWeightTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        deliveryTypeComboBox = new javax.swing.JComboBox<>();
        createPackageButton = new javax.swing.JToggleButton();
        jLabel6 = new javax.swing.JLabel();
        packageFeeTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        homeLabel = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        senderAddressTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        senderContactTextField = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        serviceFeeTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        receipientAddressTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Create Parcel");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Receipient Contact:");

        receipientContactTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        receipientContactTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receipientContactTextFieldActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setText("Receipient Name:");

        receipientNameTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel4.setText("Delivery Type:");

        packageWeightTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel5.setText("Parcel Weight:");

        deliveryTypeComboBox.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        deliveryTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Economy Delivery", "Express Delivery" }));

        createPackageButton.setText("Create Parcel");
        createPackageButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createPackageButtonMouseClicked(evt);
            }
        });
        createPackageButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPackageButtonActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel6.setText("Service Fee:");

        packageFeeTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        packageFeeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                packageFeeTextFieldActionPerformed(evt);
            }
        });
        packageFeeTextField.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                packageFeeTextFieldPropertyChange(evt);
            }
        });
        packageFeeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                packageFeeTextFieldKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                packageFeeTextFieldKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Logout");

        homeLabel.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        homeLabel.setText("Home");
        homeLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeLabelMouseClicked(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel10.setText("Address:");

        senderAddressTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel11.setText("Contact:");

        senderContactTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel12.setText(" Parcel Fee:");

        serviceFeeTextField.setEditable(false);
        serviceFeeTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        serviceFeeTextField.setText("0.00");
        serviceFeeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                serviceFeeTextFieldActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel13.setText("Receipient Address:");

        receipientAddressTextField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        receipientAddressTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receipientAddressTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(serviceFeeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(createPackageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(147, 147, 147)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(143, 143, 143)
                                .addComponent(homeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(senderContactTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(senderAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(receipientContactTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(packageWeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(receipientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(deliveryTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(packageFeeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(receipientAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(homeLabel)
                        .addComponent(jLabel8))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(senderContactTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(senderAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(receipientNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(receipientContactTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel13)
                    .addComponent(receipientAddressTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(packageWeightTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deliveryTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(packageFeeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createPackageButton, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(serviceFeeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void createPackageButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPackageButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_createPackageButtonActionPerformed

    private void receipientContactTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receipientContactTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receipientContactTextFieldActionPerformed

    private void packageFeeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_packageFeeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_packageFeeTextFieldActionPerformed

    private void createPackageButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createPackageButtonMouseClicked
        // TODO add your handling code here:
        //currentUser.getFirstName()+" "+currentUser.getLastName();
        String senderName = currentUser.getFirstName()+" "+currentUser.getLastName();
        String senderContact = senderContactTextField.getText();
        String senderAddress = senderAddressTextField.getText();
        String receipientName = receipientNameTextField.getText();
        String receipientContact = receipientContactTextField.getText();
        String receipientAddress = receipientAddressTextField.getText();
        String packageWeight = packageWeightTextField.getText();
        String deliveryType = String.valueOf(deliveryTypeComboBox.getSelectedItem());
        String packagePayment = packageFeeTextField.getText();
        String serviceFee = serviceFeeTextField.getText();
        int confirm = JOptionPane.showConfirmDialog(null, String.format("Are you sure you want to add parcel?" ), "Confirm Create Parcel", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            Parcel newParcel = new Parcel(senderName, senderAddress,senderContact,receipientName, receipientAddress, receipientContact, packageWeight, deliveryType, Double.parseDouble(packagePayment), Double.parseDouble(serviceFee),"-");
            
            String secretKey = Utils.createParcel(newParcel,currentUser);
            JOptionPane.showMessageDialog(null, "Successfully created parcel! Please proceed to escrow agreement");
            JOptionPane.showMessageDialog(null, "The following is the secret key for this parcel! Please keep it securely "+secretKey);
            refresh();
              
        }
    }//GEN-LAST:event_createPackageButtonMouseClicked

    private void serviceFeeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_serviceFeeTextFieldActionPerformed
        // TODO add your handling code here:
        if(serviceFeeTextField.getText()!=null){
            serviceFeeTextField.setText("100.00");
        }
    }//GEN-LAST:event_serviceFeeTextFieldActionPerformed

    private void receipientAddressTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receipientAddressTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_receipientAddressTextFieldActionPerformed

    private void homeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeLabelMouseClicked
        // TODO add your handling code here:
        new UserHomeGUI(currentUser).setVisible(true);
        dispose();
    }//GEN-LAST:event_homeLabelMouseClicked

    private void packageFeeTextFieldPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_packageFeeTextFieldPropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_packageFeeTextFieldPropertyChange

    private void packageFeeTextFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packageFeeTextFieldKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_packageFeeTextFieldKeyPressed

    private void packageFeeTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_packageFeeTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_packageFeeTextFieldKeyTyped

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
            java.util.logging.Logger.getLogger(CreatePackageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreatePackageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreatePackageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreatePackageGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreatePackageGUI().setVisible(true);
            }
        });
    }
    
    private void refresh() {
        //loadComboBox();

        createPackageButton.setEnabled(false);

        //reset input fields to empty
        receipientNameTextField.setText("");
        receipientContactTextField.setText("");
        senderContactTextField.setText("");
        senderAddressTextField.setText("");
        receipientAddressTextField.setText("");
        packageFeeTextField.setText("");
        packageWeightTextField.setText("");
        serviceFeeTextField.setText("");
    }
        
    private void initialisedDocumentListener() {
        DocumentListener inputListener = new DocumentListener() {
            private void updateButtonState() {
                boolean inputsFilled = Utils.textFieldsFilled(senderContactTextField,senderAddressTextField,receipientNameTextField, receipientContactTextField, receipientAddressTextField,packageWeightTextField, packageFeeTextField);
                if(inputsFilled==true){
                    Double packageWeight = Double.parseDouble(packageWeightTextField.getText());
                    Double escrowFee = Double.parseDouble(packageFeeTextField.getText());
                    String deliveryType = String.valueOf(deliveryTypeComboBox.getSelectedItem());
                    Double deliveryFee;
                    if(deliveryType.equals("Economy Delivery")){
                        deliveryFee = 50.00;
                    }
                    else{
                        deliveryFee = 100.00;
                    }
                    String serviceFee = String.valueOf(packageWeight+escrowFee+deliveryFee);
                    serviceFeeTextField.setText(serviceFee);
                }
                createPackageButton.setEnabled(inputsFilled);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateButtonState();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateButtonState();
            }

        };

        receipientNameTextField.getDocument().addDocumentListener(inputListener);
        receipientContactTextField.getDocument().addDocumentListener(inputListener);
        packageWeightTextField.getDocument().addDocumentListener(inputListener);
        packageFeeTextField.getDocument().addDocumentListener(inputListener);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton createPackageButton;
    private javax.swing.JComboBox<String> deliveryTypeComboBox;
    private javax.swing.JLabel homeLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField packageFeeTextField;
    private javax.swing.JTextField packageWeightTextField;
    private javax.swing.JTextField receipientAddressTextField;
    private javax.swing.JTextField receipientContactTextField;
    private javax.swing.JTextField receipientNameTextField;
    private javax.swing.JTextField senderAddressTextField;
    private javax.swing.JTextField senderContactTextField;
    private javax.swing.JTextField serviceFeeTextField;
    // End of variables declaration//GEN-END:variables
}
