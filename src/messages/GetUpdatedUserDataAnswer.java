/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package messages;

import DBClasses.User;

/**
 * 
 * @author sascha
 */
public class GetUpdatedUserDataAnswer implements IVBMessage {

	private User payload;

	public GetUpdatedUserDataAnswer(User user) {
		payload = user;
	}

	@Override
	public MessageType MsgType() {
		return IVBMessage.MessageType.GetUpdatedUserDataAnswer;
	}

	@Override
	public Object getPayload() {
		return payload;
	}

}
