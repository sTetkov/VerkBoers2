
import graphicalClient.MainPanel;
import HelpCLasses.ClientConnectionManager;


public class GraphicalClient {

    private static ClientConnectionManager ccm;
    private static MainPanel mainPanel;
    
    public static void main(String[] args) {
	ccm=new ClientConnectionManager();
	ccm = new ClientConnectionManager();
	ccm.setHostName("localhost");
	ccm.setPort(10000);
	mainPanel=new MainPanel();
	mainPanel.show();
    }

}
