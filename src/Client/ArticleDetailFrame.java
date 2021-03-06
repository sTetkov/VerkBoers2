/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import DBClasses.Article;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import messages.Pair;
import messages.ReducedUserData;

/**
 * 
 * @author sascha
 */
public class ArticleDetailFrame extends javax.swing.JFrame implements
		IClientGUIListener {

	public void setNewArticle(boolean newArticle) {
		this.newArticle = newArticle;
	}

	public void setIsUserArticle(boolean isUserArticle) {
		this.isUserArticle = isUserArticle;
	}

	public void setCore(ClientCore core) {
		this.core = core;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public void setRud(ReducedUserData rud) {
		this.rud = rud;
	}

	private boolean newArticle;
	private boolean isUserArticle;
	private ClientCore core;
	private Article article;
	private ReducedUserData rud;

	/**
	 * Creates new form ArticleDetailFrame
	 */
	public ArticleDetailFrame() {
		initComponents();
	}

	public void fillFields(Pair<Article, ReducedUserData> data,
			ClientCore core, boolean editableDetails) {
		if (data == null) {
			newArticle = true;
			article = new Article(core.getUser().getId());
		} else {
			article = data.left;
			newArticle = false;
			rud = data.right;
		}
		this.core = core;
		isUserArticle = editableDetails || newArticle;
		if (!newArticle) {
			idLabel.setText("" + article.getId());
			shortDescField.setText(article.getShortDescription());
			longDescField.setText(article.getLongDescription());
			weightField.setText("" + article.getWeight());
			amountField.setText("" + article.getAmount());
			grossPriceField.setText("" + article.getGrossPrice());
			vatField.setText("" + article.getVat());
			netPriceField.setText("" + article.getNetPrice());
			DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
			if (article.getExpDate() != null)
				dateField.setText(df.format(article.getExpDate()));
			else
				dateField.setText("31/12/2100");
		}
		if (!isUserArticle) {
			jButton1.setVisible(false);
			shortDescField.setEditable(false);
			longDescField.setEditable(false);
			weightField.setEditable(false);
			amountField.setEditable(false);
			grossPriceField.setEditable(false);
			vatField.setEditable(false);
			netPriceField.setEditable(false);
			dateField.setEditable(false);
			sellerLabel.setText("Offered by " + rud.getName());
			if (rud.getAddress() != null)
				sellerLabel.setText(sellerLabel.getText() + " located in "
						+ rud.getAddress());
		}
		// validate();
		// repaint();
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
		idLabel = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		shortDescField = new javax.swing.JTextField();
		jLabel3 = new javax.swing.JLabel();
		longDescField = new javax.swing.JTextField();
		jLabel4 = new javax.swing.JLabel();
		jLabel5 = new javax.swing.JLabel();
		jLabel6 = new javax.swing.JLabel();
		jLabel7 = new javax.swing.JLabel();
		jLabel8 = new javax.swing.JLabel();
		jLabel9 = new javax.swing.JLabel();
		jButton1 = new javax.swing.JButton();
		weightField = new javax.swing.JFormattedTextField();
		amountField = new javax.swing.JFormattedTextField();
		dateField = new javax.swing.JFormattedTextField();
		sellerLabel = new javax.swing.JLabel();
		jButton2 = new javax.swing.JButton();
		grossPriceField = new javax.swing.JFormattedTextField();
		vatField = new javax.swing.JFormattedTextField();
		netPriceField = new javax.swing.JFormattedTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1.setText("Article ID:");

		jLabel2.setText("Short Description:");

		jLabel3.setText("Long Description:");

		jLabel4.setText("Weight (kg):");

		jLabel5.setText("Amount (units):");

		jLabel6.setText("Gross price:");

		jLabel7.setText("VAT (%):");

		jLabel8.setText("Net Price:");

		jLabel9.setText("Expiration Date (dd/mm/yyyy):");

		jButton1.setText("Save");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		weightField
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));

		amountField
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));

		dateField
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.DateFormatter(
								new java.text.SimpleDateFormat("dd/MM/yyyy"))));

		jButton2.setText("Cancel");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		grossPriceField
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));

		vatField.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
				new javax.swing.text.NumberFormatter(
						new java.text.DecimalFormat("#0.00"))));

		netPriceField
				.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(
						new javax.swing.text.NumberFormatter(
								new java.text.DecimalFormat("#0.00"))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														sellerLabel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel2)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		shortDescField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel3)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		longDescField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel4)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		weightField))
												.addGroup(
														layout.createSequentialGroup()
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel5)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel1)
																								.addGap(18,
																										18,
																										18)
																								.addComponent(
																										idLabel))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jButton1)
																								.addPreferredGap(
																										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																								.addComponent(
																										jButton2)))
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		amountField))
												.addGroup(
														javax.swing.GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		jLabel6)
																.addGap(18, 18,
																		18)
																.addComponent(
																		grossPriceField,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		492,
																		javax.swing.GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel9)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		dateField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel8)
																.addGap(18, 18,
																		18)
																.addComponent(
																		netPriceField))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		jLabel7)
																.addGap(31, 31,
																		31)
																.addComponent(
																		vatField)))
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
												.addComponent(idLabel))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														shortDescField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel2))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														longDescField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel3))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														weightField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel4))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														amountField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel5))
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														grossPriceField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel6))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(
														vatField,
														javax.swing.GroupLayout.PREFERRED_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel7))
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(5, 5, 5)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						jLabel8)
																				.addComponent(
																						netPriceField,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(38, 38,
																		38)
																.addGroup(
																		layout.createParallelGroup(
																				javax.swing.GroupLayout.Alignment.BASELINE)
																				.addComponent(
																						dateField,
																						javax.swing.GroupLayout.PREFERRED_SIZE,
																						javax.swing.GroupLayout.DEFAULT_SIZE,
																						javax.swing.GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel9))))
								.addGap(7, 7, 7)
								.addComponent(sellerLabel,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										38, Short.MAX_VALUE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.BASELINE)
												.addComponent(jButton1)
												.addComponent(jButton2))
								.addContainerGap()));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		this.setVisible(false);
	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
			
		article.setShortDescription(shortDescField.getText());
		article.setLongDescription(longDescField.getText());
		article.setWeight(Float.parseFloat(weightField.getText().replace(',','.')));
		article.setAmount(Float.parseFloat(amountField.getText().replace(',','.')));
		if ("".equals(grossPriceField.getText())||"0.0".equals(grossPriceField.getText().replace(',','.'))) {
			float vat = Float.parseFloat(vatField.getText().replace('%', ' ').replace(',','.'));
			float netPrice = Float.parseFloat(netPriceField.getText().replace(',','.'));
			float grossPrice = netPrice / (1 + vat / 100);
			article.setGrossPrice(grossPrice);
		} else
			article.setGrossPrice(Float.parseFloat(grossPriceField.getText().replace(',','.')));
		if ("".equals(vatField.getText())||"0.0".equals(vatField.getText().replace(',','.'))) {
			float grossPrice = Float.parseFloat(grossPriceField.getText().replace(',','.'));
			float netPrice = Float.parseFloat(netPriceField.getText().replace(',','.'));
			float vat = grossPrice / netPrice * 100;
			article.setVat(vat);
		} else
			article.setVat(Float.parseFloat(vatField.getText().replace(',','.')));
		if ("".equals(netPriceField.getText())||"0.0".equals(netPriceField.getText().replace(',','.'))) {
			float vat = Float.parseFloat(vatField.getText().replace('%', ' ').replace(',','.'));
			float grossPrice = Float.parseFloat(grossPriceField.getText().replace(',','.'));
			float netPrice = grossPrice * (1 + vat / 100);
			article.setNetPrice(netPrice);
		} else
			article.setNetPrice(Float.parseFloat(netPriceField.getText().replace(',','.')));
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		try {
			java.util.Date date = df.parse(dateField.getText());
			java.sql.Date sqlDate;
			sqlDate = new java.sql.Date(date.getYear(), date.getMonth(),
					date.getDate());
			article.setExpDate(sqlDate);
		} catch (ParseException ex) {
			JOptionPane.showMessageDialog(null,
					"Please use the format dd/MM/yyyy for the expiration date");
			return;
		}
		if (newArticle)
			core.AddNewArticle(article, this);
		else
			core.ChangeArticleData(article, this);

	}// GEN-LAST:event_jButton1ActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JFormattedTextField amountField;
	private javax.swing.JFormattedTextField dateField;
	private javax.swing.JFormattedTextField grossPriceField;
	private javax.swing.JLabel idLabel;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel jLabel3;
	private javax.swing.JLabel jLabel4;
	private javax.swing.JLabel jLabel5;
	private javax.swing.JLabel jLabel6;
	private javax.swing.JLabel jLabel7;
	private javax.swing.JLabel jLabel8;
	private javax.swing.JLabel jLabel9;
	private javax.swing.JTextField longDescField;
	private javax.swing.JFormattedTextField netPriceField;
	private javax.swing.JLabel sellerLabel;
	private javax.swing.JTextField shortDescField;
	private javax.swing.JFormattedTextField vatField;
	private javax.swing.JFormattedTextField weightField;

	// End of variables declaration//GEN-END:variables

	@Override
	public void confirmMessageSent() {
		// TODO something
	}

	@Override
	public void positiveAnswerReceived(Object payload) {
		if (newArticle)
			JOptionPane.showMessageDialog(null, "Article added");
		else
			JOptionPane.showMessageDialog(null, "Article modififed");
		this.setVisible(false);
	}

	@Override
	public void failureAnswerReceived(Object payload) {
		JOptionPane.showMessageDialog(null, "Operation failed: "
				+ (String) payload);
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
}
