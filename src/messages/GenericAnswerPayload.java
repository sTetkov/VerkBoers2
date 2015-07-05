package messages;

import java.io.Serializable;

public class GenericAnswerPayload implements Serializable {

    private boolean operationSuccess;

    public boolean isOperationSuccess() {
	return operationSuccess;
    }

    public void setOperationSuccess(boolean operationSuccess) {
	this.operationSuccess = operationSuccess;
    }

    public String getMsg() {
	return msg;
    }

    public void setMsg(String msg) {
	this.msg = msg;
    }

    private String msg;

    public GenericAnswerPayload(boolean b, String msg) {
	this.operationSuccess = b;
	this.msg = msg;
    }
}
