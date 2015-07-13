/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import messages.IVBMessage;

/**
 * 
 * @author sascha
 */
public interface IServerAnswerToRequest {
	public void requestExecuted(IVBMessage msg);

	public void requestDenied(IVBMessage msg);

	public void requestFailed(IVBMessage msg);

}
