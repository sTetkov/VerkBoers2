package Server;

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import DBClasses.Article;
import DBClasses.Transaction;
import DBClasses.User;

public class MailManager implements Runnable {

	final String from = "sascha.tetkov@uni-jena.de";
	final String host = "smtp.uni-jena.de";
	final String username = "ka42juf";
	final String pwd = "47X2reti1";
	final String smtpPort = "587";
	
	private int mailType; 	//0: Confirmation Code
				//1: Transaction Mail
                                //2: Private mail

	private Article art;
	private Transaction trns;
	private User Seller;
	private User Buyer;
	
	private String eMail;
	private String code;
        
        private String adminUserName;
    private String receipient;
    private String message;
	
	public MailManager(Article art, Transaction trns, User Seller, User Buyer)
	{
		mailType=1;
		this.art=art;
		this.trns=trns;
		this.Seller=Seller;
		this.Buyer=Buyer;
		
	}
	
	public MailManager(String eMail, String code)
	{
		mailType=0;
		this.eMail=eMail;
		this.code=code;
	}
	
        public MailManager(String receipient,String username, String message)
        {
            mailType=2;
            this.receipient=receipient;
            this.adminUserName=username;
            this.message=message;
        }
        
	private Properties getProperties() {
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.user", username);
		properties.setProperty("mail.password", pwd);
		properties.setProperty("mail.smtp.socketFactory.port", smtpPort);
		properties.setProperty("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.port", smtpPort);

		return properties;
	}

	public boolean SendTransactionMail() {

		Session session = Session.getDefaultInstance(getProperties(),
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, pwd);
					}
				});
		String to = Seller.geteMail();
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("You made a sale");
			message.setText("You have sold the following article: "
					+ art.getShortDescription() + "\n" + "Amount: "
					+ trns.getAmount() + "\n" + "for a Gross Unit Price of "
					+ trns.getGrossPrice() + " Euro\n" + "to "
					+ Buyer.getSurname() + " " + Buyer.getName());
			Transport.send(message);
			System.out.println("Sent message successfully....");

			to = Buyer.geteMail();
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));
			message.setSubject("You have bought something");
			message.setText("You have bought the following article: "
					+ art.getShortDescription() + "\n" + "Amount: "
					+ trns.getAmount() + "\n" + "for a Gross Unit Price of "
					+ trns.getGrossPrice() + " Euro\n" + "from "
					+ Seller.getSurname() + " " + Seller.getName());
			Transport.send(message);
			System.out.println("Sent message successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}

		return true;
	}

	public void sendConfirmationCode() {
		Session session = Session.getDefaultInstance(getProperties(),
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, pwd);
					}
				});
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					eMail));
			message.setSubject("Confirmation code");
			message.setText("To complete your registration use the code "
					+ code);
			Transport.send(message);
			System.out.println("Sent message successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}

	}

	public void sendMessageTo() {
		Session session = Session.getDefaultInstance(getProperties(),
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, pwd);
					}
				});
		try {
			MimeMessage mMessage = new MimeMessage(session);
			mMessage.setFrom(new InternetAddress(from));
			mMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
					receipient));
			mMessage.setSubject("Message from "+adminUserName+" from Verkaufsboerse");
			mMessage.setText(this.message);
			Transport.send(mMessage);
			System.out.println("Sent message successfully....");

		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

	@Override
	public void run() {
		switch(mailType)
		{
		case 0:
                       sendConfirmationCode();
			break;
		case 1:
			SendTransactionMail();
			break;
                case 2:
                    sendMessageTo();
                    break;
		}
		
	}
}