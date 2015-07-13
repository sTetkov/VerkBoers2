/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

/**
 * 
 * @author sascha
 */
public class GetUpdatedUserDataRequest implements IVBMessage {

	private Integer payload;

	public GetUpdatedUserDataRequest(int userID) {
		payload = new Integer(userID);
	}

	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.GetUpdatedUserDataRequest;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

}
