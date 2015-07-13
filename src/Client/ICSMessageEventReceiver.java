package Client;

import messages.IVBMessage;

public interface ICSMessageEventReceiver {
	public void MessageSent(int opID);

	public void AnswerReceived(int opID, IVBMessage answer);

	public void CommunicationError(int opID, IVBMessage answer);
}
