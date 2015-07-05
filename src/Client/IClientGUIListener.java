package Client;

public interface IClientGUIListener {

    void confirmMessageSent();
    void positiveAnswerReceived(Object payload);
    void failureAnswerReceived(Object payload);
    void communicationErrorReceived(Object payload);

}
