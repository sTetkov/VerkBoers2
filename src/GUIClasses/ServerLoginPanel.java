/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUIClasses;

/**
 *
 * @author sascha
 */
public class ServerLoginPanel extends javax.swing.JPanel {

    /**
     * Creates new form ClientLoginPanel
     */
    public ServerLoginPanel() {
	initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed"
    // desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

	jLabel1 = new javax.swing.JLabel();
	jLabel2 = new javax.swing.JLabel();
	uNameField = new javax.swing.JTextField();
	pwdField = new javax.swing.JTextField();
	okButton = new javax.swing.JButton();
	newUserButton = new javax.swing.JButton();
	quitButton = new javax.swing.JButton();
	jLabel3 = new javax.swing.JLabel();

	jLabel1.setText("Password:");

	jLabel2.setText("Username:");

	okButton.setText("OK");
	okButton.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		okButtonActionPerformed(evt);
	    }
	});

	newUserButton.setText("New User");
	newUserButton.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		newUserButtonActionPerformed(evt);
	    }
	});

	quitButton.setText("Quit");
	quitButton.addActionListener(new java.awt.event.ActionListener() {
	    public void actionPerformed(java.awt.event.ActionEvent evt) {
		quitButtonActionPerformed(evt);
	    }
	});

	jLabel3.setText("Log In");

	javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
	this.setLayout(layout);
	layout.setHorizontalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addContainerGap()
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
							layout.createSequentialGroup()
								.addComponent(
									jLabel1)
								.addGap(18, 18,
									18)
								.addGroup(
									layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
											layout.createSequentialGroup()
												.addComponent(
													okButton)
												.addGap(18,
													18,
													18)
												.addComponent(
													newUserButton)
												.addPreferredGap(
													javax.swing.LayoutStyle.ComponentPlacement.RELATED,
													153,
													Short.MAX_VALUE)
												.addComponent(
													quitButton))
										.addComponent(
											pwdField)))
						.addGroup(
							layout.createSequentialGroup()
								.addComponent(
									jLabel2)
								.addGap(18, 18,
									18)
								.addComponent(
									uNameField)))
				.addContainerGap())
		.addGroup(
			layout.createSequentialGroup()
				.addGap(184, 184, 184)
				.addComponent(jLabel3)
				.addContainerGap(
					javax.swing.GroupLayout.DEFAULT_SIZE,
					Short.MAX_VALUE)));
	layout.setVerticalGroup(layout
		.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
		.addGroup(
			layout.createSequentialGroup()
				.addContainerGap(77, Short.MAX_VALUE)
				.addComponent(jLabel3)
				.addGap(18, 18, 18)
				.addGroup(
					layout.createParallelGroup(
						javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
							javax.swing.GroupLayout.Alignment.TRAILING,
							layout.createSequentialGroup()
								.addGap(137,
									137,
									137)
								.addGroup(
									layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(
											quitButton)
										.addComponent(
											newUserButton)
										.addComponent(
											okButton))
								.addContainerGap())
						.addGroup(
							layout.createSequentialGroup()
								.addGroup(
									layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(
											jLabel2)
										.addComponent(
											uNameField,
											javax.swing.GroupLayout.PREFERRED_SIZE,
											javax.swing.GroupLayout.DEFAULT_SIZE,
											javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(18, 18,
									18)
								.addGroup(
									layout.createParallelGroup(
										javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(
											jLabel1)
										.addComponent(
											pwdField,
											javax.swing.GroupLayout.PREFERRED_SIZE,
											javax.swing.GroupLayout.DEFAULT_SIZE,
											javax.swing.GroupLayout.PREFERRED_SIZE))
								.addContainerGap(
									javax.swing.GroupLayout.DEFAULT_SIZE,
									Short.MAX_VALUE)))));

	jLabel2.getAccessibleContext().setAccessibleName("Username");
	jLabel2.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_okButtonActionPerformed
	// TODO add your handling code here:
    }// GEN-LAST:event_okButtonActionPerformed

    private void newUserButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_newUserButtonActionPerformed
	// TODO add your handling code here:
    }// GEN-LAST:event_newUserButtonActionPerformed

    private void quitButtonActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_quitButtonActionPerformed
	// TODO add your handling code here:
    }// GEN-LAST:event_quitButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton newUserButton;
    private javax.swing.JButton okButton;
    private javax.swing.JTextField pwdField;
    private javax.swing.JButton quitButton;
    private javax.swing.JTextField uNameField;
    // End of variables declaration//GEN-END:variables
}
