package messages;

public class LoginMessageAnswerPayload {
private boolean loginSucces;
public boolean isLoginSucces() {
    return loginSucces;
}
public void setLoginSucces(boolean loginSucces) {
    this.loginSucces = loginSucces;
}
public String getFailureReason() {
    return failureReason;
}
public void setFailureReason(String failureReason) {
    this.failureReason = failureReason;
}
private String failureReason;
}
