package messages;

import java.io.Serializable;

public class NewUserConfirmationCodePayload implements Serializable {
    private String eMail;

    public String geteMail() {
	return eMail;
    }

    public void seteMail(String eMail) {
	this.eMail = eMail;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    private String code;

}
