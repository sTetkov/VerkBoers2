/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import DBClasses.BankConnection;
import DBClasses.SEPA;
import DBClasses.User;
import javax.swing.JOptionPane;

/**
 * 
 * @author sascha
 */
public class CreateNewUserPanel extends javax.swing.JPanel implements
		IClientGUIListener {

	private ClientCore core;
	private ClientMainFrame parent;
	private boolean createUser;
	private boolean waitForUpdatedData;

	/**
	 * Creates new form CreateNewUserPanel
	 */
	public CreateNewUserPanel(ClientCore core, ClientMainFrame parent,
			boolean createUser) {
		initComponents();
		this.core = core;
		this.parent = parent;
		this.createUser = createUser;
		waitForUpdatedData = false;
		if (!createUser) {
			fillUserFields();
			jLabel9.setVisible(false);
			jLabel15.setVisible(false);
			pwdField1.setVisible(false);
			pwdField2.setVisible(false);
		} else
			jTabbedPane1.setVisible(false);
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
		surnameField = new javax.swing.JTextField();
		nameField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		strField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		jLabel4 = new javax.swing.JLabel();
		mailField = new javax.swing.JTextField();
		jLabel5 = new javax.swing.JLabel();
		countryField = new javax.swing.JTextField();
		plzField = new javax.swing.JTextField();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		nrField = new javax.swing.JTextField();
		cityField = new javax.swing.JTextField();
		jLabel8 = new javax.swing.JLabel();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jPanel1 = new javax.swing.JPanel();
		jLabel10 = new javax.swing.JLabel();
		IBANField = new javax.swing.JTextField();
		jLabel11 = new javax.swing.JLabel();
		bicField = new javax.swing.JTextField();
		jPanel2 = new javax.swing.JPanel();
		jLabel12 = new javax.swing.JLabel();
		bcActField = new javax.swing.JTextField();
		jLabel13 = new javax.swing.JLabel();
		bcBnkNrField = new javax.swing.JTextField();
		jLabel14 = new javax.swing.JLabel();
		bcBnkNameField = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jLabel9 = new javax.swing.JLabel();
		pwdField1 = new javax.swing.JTextField();
		pwdField2 = new javax.swing.JTextField();
		jLabel15 = new javax.swing.JLabel();

		jLabel1.setText("Name:");

		jLabel2.setText("Surname:");

		jLabel3.setText("E-Mail:");

		jLabel4.setText("Street:");

		jLabel5.setText("City:");

		jLabel6.setText("Nr.:");

		jLabel7.setText("Postal Code:");

		nrField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				nrFieldActionPerformed(evt);
			}
		});

		cityField.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				cityFieldActionPerformed(evt);
			}
		});

		jLabel8.setText("Country:");

		jLabel10.setText("IBAN:");

		jLabel11.setText("BIC_SWIFT:");

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(
				jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(jLabel10)
														.addComponent(jLabel11))
										.addGap(4, 4, 4)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																IBANField,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																262,
																Short.MAX_VALUE)
														.addComponent(bicField))
										.addContainerGap()));
		jPanel1Layout
				.setVerticalGroup(jPanel1Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel1Layout
										.createSequentialGroup()
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel10)
														.addComponent(
																IBANField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel1Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel11)
														.addComponent(
																bicField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 42, Short.MAX_VALUE)));

		jTabbedPane1.addTab("SEPA", jPanel1);

		jLabel12.setText("Act. Nr:");

		bcActField.setText("jTextField12");

		jLabel13.setText("Bank Nr.:");

		bcBnkNrField.setText("jTextField13");

		jLabel14.setText("Bank Name:");

		bcBnkNameField.setText("jTextField14");

		javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(
				jPanel2);
		jPanel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel12)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				bcActField))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel13)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				bcBnkNrField))
														.addGroup(
																jPanel2Layout
																		.createSequentialGroup()
																		.addComponent(
																				jLabel14)
																		.addPreferredGap(
																				javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(
																				bcBnkNameField,
																				javax.swing.GroupLayout.DEFAULT_SIZE,
																				268,
																				Short.MAX_VALUE)))
										.addContainerGap()));
		jPanel2Layout
				.setVerticalGroup(jPanel2Layout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel12)
														.addComponent(
																bcActField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel13)
														.addComponent(
																bcBnkNrField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												jPanel2Layout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(jLabel14)
														.addComponent(
																bcBnkNameField,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.PREFERRED_SIZE))
										.addGap(0, 5, Short.MAX_VALUE)));

		jTabbedPane1.addTab("Bank Koordinaten", jPanel2);

		jButton1.setText("OK");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Cancel");

		jLabel9.setText("Password:");

		jLabel15.setText("Repeat Password:");

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
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		nameField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		surnameField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel3)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		mailField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel4)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		strField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel6)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		nrField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel7)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		plzField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel5)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		cityField))
												.addComponent(jTabbedPane1)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jButton1)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jButton2)
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel8)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		countryField))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		jLabel9)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		pwdField1))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addComponent(
																		jLabel15)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		pwdField2)))
								.addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel1)
												.addComponent(
														nameField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel2)
												.addComponent(
														surnameField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel3)
												.addComponent(
														mailField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel4)
												.addComponent(
														strField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel6)
												.addComponent(
														nrField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel7)
												.addComponent(
														plzField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel5)
												.addComponent(
														cityField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel8)
												.addComponent(
														countryField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel9)
												.addComponent(
														pwdField1,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jLabel15)
												.addComponent(
														pwdField2,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										22, Short.MAX_VALUE)
								.addComponent(jTabbedPane1,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										151,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton1)
												.addComponent(jButton2))
								.addContainerGap()));
	}// </editor-fold>//GEN-END:initComponents

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		User user = fillUser();
		if (createUser) {
			if (user != null) {
				core.CreateUser(user, mailField.getText(), pwdField1.getText(),
						this);
				jButton1.disable();
			}
		} else {
			if (user != null) {
				user.setId(core.getUser().getId());
				user.setState(core.getUser().getState());
				core.ChangeUserData(user, this);
				jButton1.disable();
			}
		}
	}// GEN-LAST:event_jButton1ActionPerformed

	private void cityFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cityFieldActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_cityFieldActionPerformed

	private void nrFieldActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_nrFieldActionPerformed
		// TODO add your handling code here:
	}// GEN-LAST:event_nrFieldActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JTextField IBANField;
	private javax.swing.JTextField bcActField;
	private javax.swing.JTextField bcBnkNameField;
	private javax.swing.JTextField bcBnkNrField;
	private javax.swing.JTextField bicField;
	private javax.swing.JTextField cityField;
	private javax.swing.JTextField countryField;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel10;
	private javax.swing.JLabel jLabel11;
	private javax.swing.JLabel jLabel12;
	private javax.swing.JLabel jLabel13;
	private javax.swing.JLabel jLabel14;
	private javax.swing.JLabel jLabel15;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JPanel jPanel1;
	private javax.swing.JPanel jPanel2;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JTextField mailField;
	private javax.swing.JTextField nameField;
	private javax.swing.JTextField nrField;
	private javax.swing.JTextField plzField;
	private javax.swing.JTextField pwdField1;
	private javax.swing.JTextField pwdField2;
	private javax.swing.JTextField strField;
	private javax.swing.JTextField surnameField;

	// End of variables declaration//GEN-END:variables

	@Override
	public void confirmMessageSent() {
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
	}

	@Override
	public void positiveAnswerReceived(Object payload) {
		if (waitForUpdatedData) {
			waitForUpdatedData = false;
			fillUserFields();
			return;
		}
		if (createUser)
                {
			JOptionPane.showMessageDialog(null,
					"New user requested, wait for your confirmation code");
                parent.backToLogin();
        }
		else {
			JOptionPane.showMessageDialog(null, "User data changed");
			core.GetUpdatedUserData(this);
			waitForUpdatedData = true;
		}
		parent.backToStart();
	}

	@Override
	public void failureAnswerReceived(Object payload) {
		String message = "";
		if (payload != null) {
			message = (String) payload;
		} else
			message = "There was an unspecified communication error.";
		JOptionPane.showMessageDialog(null, message);
		jButton1.enable();
	}

	@Override
	public void communicationErrorReceived(Object payload) {
		String message = "";
		if (payload != null) {
			message = "Comunication error: " + (String) payload;
		} else
			message = "There was an unspecified communication error.";
		JOptionPane.showMessageDialog(null, message);
		jButton1.enable();
	}

	private User fillUser() {
		if (!checkUserData())
			return null;
		User user = new User();
		user.setName(nameField.getText());
		user.setSurname(surnameField.getText());
		user.seteMail(mailField.getText());
		user.setStr(strField.getText());
		user.setNr(nrField.getText());
		user.setPlz(plzField.getText());
		user.setCity(cityField.getText());
		user.setCountry(countryField.getText());
		if (!createUser) {
			if (jTabbedPane1.getSelectedIndex() == 0) {
				SEPA sepa = null;
				if (core.getUser().getBankData().getType().equals("SEPA"))
					sepa = ((SEPA) core.getUser().getBankData());
				else
					sepa = new SEPA();
				sepa.setIban(IBANField.getText());
				sepa.setBic_swift(bicField.getText());
				sepa.setUserId(core.getUser().getId());
				user.setBankData(sepa);

			} else {
				BankConnection bnk = null;
				if (core.getUser().getBankData().getType()
						.equals("Bankverbindung"))
					bnk = (BankConnection) core.getUser().getBankData();
				else
					bnk = new BankConnection();
				bnk.setAcctNr(bcActField.getText());
				bnk.setBankNr(bcBnkNrField.getText());
				bnk.setUserId(core.getUser().getId());
				bnk.setBankName(bcBnkNameField.getText());
				user.setBankData(bnk);
			}
		}
		return user;
	}

	private boolean checkUserData() {
		// throw new UnsupportedOperationException("Not supported yet."); //To
		// change body of generated methods, choose Tools | Templates.
		return true;
	}

	private void fillUserFields() {
		User user = core.getUser();
		nameField.setText(user.getName());
		surnameField.setText(user.getSurname());
		mailField.setText(user.geteMail());
		strField.setText(user.getStr());
		nrField.setText(user.getNr());
		plzField.setText(user.getPlz());
		cityField.setText(user.getCity());
		countryField.setText(user.getCountry());
		if (user.getBankData() != null) {
			if (user.getBankData() instanceof SEPA) {
				SEPA sepa = (SEPA) user.getBankData();
				jTabbedPane1.setSelectedIndex(0);
				IBANField.setText(sepa.getIban());
				bicField.setText(sepa.getBic_swift());
			} else {
				BankConnection bnk = (BankConnection) user.getBankData();
				jTabbedPane1.setSelectedIndex(1);
				bcActField.setText(bnk.getAcctNr());
				bcBnkNrField.setText(bnk.getBankNr());
				bcBnkNameField.setText(bnk.getBankName());
			}
		}

	}
}
