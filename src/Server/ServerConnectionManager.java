/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import HelpCLasses.MessagePackage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import messages.IVBMessage;

/**
 *
 * @author sascha
 */
public class ServerConnectionManager implements Runnable,IServerAnswerToRequest{
    
    private int port;
    private Socket socket;
    private boolean waitingForConnection;
    private boolean running;
        
    public ServerConnectionManager(int serverPort) {
	this.port = serverPort;
	waitingForConnection = true;
    }

    public ServerConnectionManager(Socket socket) {
	this.socket = socket;
	waitingForConnection = false;
    }
    
    @Override
    public void run() {
        if (waitingForConnection)
	    waitForIncomingRequest();
	else
        {
            running=true;
	    handleRequest();
            while(running){}
        }
    }

    private void waitForIncomingRequest() {
	try {
	    ServerSocket sSocket = new ServerSocket(port);
	    while (true) {
		Socket incoming = sSocket.accept();
		new Thread(new ServerConnectionManager(incoming)).start();
	    }
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    private void handleRequest() {
	try {
	    ObjectInputStream ois = new ObjectInputStream(
		    socket.getInputStream());

	    MessagePackage msgPck = (MessagePackage) ois.readObject();
	    IVBMessage answer = null;
            ServerCore reqHandler=new ServerCore(msgPck.getMessage(),this);
            new Thread(reqHandler).start();    
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (ClassNotFoundException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	} catch (Exception e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

    }

    @Override
    public void requestExecuted(IVBMessage msg) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream());
            oos.writeObject(msg);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            running=false;
        }
        running=false;
    }

    @Override
    public void requestDenied(IVBMessage msg) {
         try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream());
            oos.writeObject(msg);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            running=false;
        }
         running=false;
    }

    @Override
    public void requestFailed(IVBMessage msg) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(
                    socket.getOutputStream());
            oos.writeObject(msg);
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServerConnectionManager.class.getName()).log(Level.SEVERE, null, ex);
            running=false;
        }
        running=false;
    }
    
}