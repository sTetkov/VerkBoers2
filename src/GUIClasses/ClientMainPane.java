package GUIClasses;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Utilities.ClientConnectionManager;

public class ClientMainPane extends JFrame {

	private String ver;
	private String propertiesFilePath;
	private ClientConnectionManager ccm;
	private JPanel currentLoadedPanel;
	
	private void loadConfiguration()
	{
		/// Hard-coded Properties
		/// TODO: Change later to load Properties from file
		ver="0.0.1";
		setSize(800,600);
        setLocationRelativeTo(null);     
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Verkaufsboerse - Client "+ver);
	}
	
	public ClientMainPane(String configFile)
	{
		propertiesFilePath=configFile;
		loadConfiguration();
		setCurrentLoadedPanel(new ClientLoginPane());
	}
	
	private void setCurrentLoadedPanel(ClientLoginPane clientLoginPane) {
		currentLoadedPanel=clientLoginPane;
		Container pane = getContentPane();
		currentLoadedPanel.setBounds(5, 5, this.getWidth()-10,this.getHeight()-10);
		pane.add(currentLoadedPanel);		
		pane.revalidate();
	}

}
