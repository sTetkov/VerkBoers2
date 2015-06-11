import java.awt.EventQueue;

import GUIClasses.ClientMainPane;


public class GUIClient {

	/**
	 * @param args
	 */
	
	private static ClientMainPane cmp;
	
	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
        
            @Override
            public void run() {
            	cmp = new ClientMainPane("./clientConfig.xml");
                cmp.setVisible(true);
            }
        });

	}

}
