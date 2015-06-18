package HelpCLasses;

import messages.IVBMessage;

public interface ICSMessageEventReceiver {
	public void MessageSent();
	public void AnswerReceived(IVBMessage answer);
	public void CommunicationError(IVBMessage answer);
}
