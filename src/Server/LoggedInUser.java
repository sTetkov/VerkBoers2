package Server;

import DBClasses.User;
import java.sql.Timestamp;

public class LoggedInUser {

    public LoggedInUser(int id, String eMail) {
	userId = id;
	this.eMail = eMail;
    }

    public int getUserId() {
	return userId;
    }

    public void setUserId(int userId) {
	this.userId = userId;
    }

    public String geteMail() {
	return eMail;
    }

    public void seteMail(String eMail) {
	this.eMail = eMail;
    }

    private int userId;
    private String eMail;
    private Timestamp tStamp;

    public boolean equals(Object usr) {
        if(this==usr) return true;
        if(usr instanceof Integer)
            if (userId == ((Integer)usr).intValue())
                return true;
        if(usr instanceof User)
            if (userId == ((User)usr).getId())
                return true;
        if(usr instanceof LoggedInUser)
            if (userId == ((LoggedInUser)usr).getUserId())
                return true;
	return false;
    }

    public String toString() {
	return userId + " - " + eMail;
    }

    public Timestamp gettStamp() {
	return tStamp;
    }

    public void StampRecord() {
	java.util.Calendar calendar = java.util.Calendar.getInstance();
	java.util.Date now = calendar.getTime();
	tStamp = new java.sql.Timestamp(now.getTime());
    }

    public void settStamp(Timestamp tStamp) {
	this.tStamp = tStamp;
    }
}
