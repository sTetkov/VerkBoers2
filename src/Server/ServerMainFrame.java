/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

/**
 * 
 * @author sascha
 */
public class ServerMainFrame extends javax.swing.JFrame {

	private String username;
	private ServerCore core;
    private String author="Sascha Tetkov";
    private String version="0.1.0";

	/**
	 * Creates new form ServerMainFrame
	 */
	public ServerMainFrame() {
		initComponents();
		ServerCore.initServerCore();
		logout();
		loginPanel();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        userMenuItem = new javax.swing.JMenuItem();
        articleMenuItem = new javax.swing.JMenuItem();
        loggedMenuItem = new javax.swing.JMenuItem();
        sendMessageMenuItem = new javax.swing.JMenuItem();
        logoutMenuItem = new javax.swing.JMenuItem();
        quitMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jMenu1.setText("Server");

        userMenuItem.setText("User");
        userMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(userMenuItem);

        articleMenuItem.setText("Articles");
        articleMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                articleMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(articleMenuItem);

        loggedMenuItem.setText("Logged Users");
        loggedMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loggedMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(loggedMenuItem);

        sendMessageMenuItem.setText("Send Message");
        sendMessageMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendMessageMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(sendMessageMenuItem);

        logoutMenuItem.setText("Log out");
        logoutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(logoutMenuItem);

        quitMenuItem.setText("Quit");
        quitMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(quitMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");

        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sendMessageMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendMessageMenuItemActionPerformed
       sendMessagePanel();
    }//GEN-LAST:event_sendMessageMenuItemActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(null, "Verkaufsboerse - Server - Ver."
				+ this.version + "\nby " + this.author);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

	private void userMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_userMenuItemActionPerformed
		userPanel();
	}// GEN-LAST:event_userMenuItemActionPerformed

	private void logoutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_logoutMenuItemActionPerformed
		logout();
		loginPanel();
	}// GEN-LAST:event_logoutMenuItemActionPerformed

	private void quitMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_quitMenuItemActionPerformed
		quit();
	}// GEN-LAST:event_quitMenuItemActionPerformed

	private void articleMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_articleMenuItemActionPerformed
		articlePanel();
	}// GEN-LAST:event_articleMenuItemActionPerformed

	private void loggedMenuItemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_loggedMenuItemActionPerformed
		loggedPanel();
	}// GEN-LAST:event_loggedMenuItemActionPerformed
	
	private void sendMessagePanel() {
		ServerMessagePanel panel = new ServerMessagePanel(username);
		this.setContentPane(panel);
		validate();
		repaint();
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(ServerMainFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(ServerMainFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(ServerMainFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(ServerMainFrame.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new ServerMainFrame().setVisible(true);
			}
		});
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem articleMenuItem;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem loggedMenuItem;
    private javax.swing.JMenuItem logoutMenuItem;
    private javax.swing.JMenuItem quitMenuItem;
    private javax.swing.JMenuItem sendMessageMenuItem;
    private javax.swing.JMenuItem userMenuItem;
    // End of variables declaration//GEN-END:variables

	private void loginPanel() {
		ServerLoginPanel panel = new ServerLoginPanel(this);
		this.setContentPane(panel);
		validate();
		repaint();
	}

	private void userPanel() {
		ServerUserPanel panel = new ServerUserPanel(this);
		this.setContentPane(panel);
		validate();
		repaint();
	}

	private void articlePanel() {
		ServerArticlePanel panel = new ServerArticlePanel();
		this.setContentPane(panel);
		validate();
		repaint();
	}

	private void loggedPanel() {
		ServerLoggedInUserPanel panel = new ServerLoggedInUserPanel();
		this.setContentPane(panel);
		validate();
		repaint();
	}

	private void quit() {
		System.exit(0);
	}

	void confirmLogin(String username) {
		this.username = username;
		userMenuItem.setEnabled(true);
		articleMenuItem.setEnabled(true);
		loggedMenuItem.setEnabled(true);
		logoutMenuItem.setEnabled(true);
                sendMessageMenuItem.setEnabled(true);
		userPanel();
	}

	private void logout() {
		userMenuItem.setEnabled(false);
		articleMenuItem.setEnabled(false);
		loggedMenuItem.setEnabled(false);
		logoutMenuItem.setEnabled(false);
                sendMessageMenuItem.setEnabled(false);
	}

}
