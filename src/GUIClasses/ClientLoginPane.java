package GUIClasses;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ClientLoginPane extends JPanel {

	private void loadGUIElements()
	{
		setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
		
		JLabel uNameLabel=new JLabel("Username:");
		JLabel pwdLabel=new JLabel("Password:");
		JTextField uNameTxtBox=new JTextField();
		JTextField pwdTxtBox=new JTextField();
		JButton okButton=new JButton("OK");
		JButton newUserButton=new JButton("New User");
		JButton quitButton=new JButton("Quit");
		
		JPanel firstLine=new JPanel();
		firstLine.setLayout(new BoxLayout(firstLine, BoxLayout.X_AXIS));
		firstLine.add(Box.createVerticalGlue());
		firstLine.add(uNameLabel);
		firstLine.add(uNameTxtBox);
		uNameTxtBox.setSize(uNameTxtBox.getWidth(), 15);
        
		JPanel secondLine=new JPanel();
		secondLine.setLayout(new BoxLayout(secondLine, BoxLayout.X_AXIS));
		secondLine.add(pwdLabel);
		secondLine.add(pwdTxtBox);
		pwdTxtBox.setSize(pwdTxtBox.getWidth(), 15);
		
		JPanel thirdLine=new JPanel();
		thirdLine.setLayout(new BoxLayout(thirdLine, BoxLayout.X_AXIS));		
		thirdLine.add(okButton);
		thirdLine.add(newUserButton);
		thirdLine.add(quitButton);
		
		add(firstLine);
		add(secondLine);
		add(thirdLine);
    }
	
	public ClientLoginPane()
	{
		loadGUIElements();
	}
}
