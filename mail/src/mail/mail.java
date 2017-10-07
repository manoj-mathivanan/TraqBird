package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {


	public static void main(String[] args) {
		try{
		// TODO Auto-generated method stub
		String  d_email = "manoj@traqbird.com",
		            d_uname = "Name",
		            d_password = "urpassword",
		            d_host = "smtp.gmail.com",
		            d_port  = "465",
		            m_to = "manoj@traqbird.com",
		            m_subject = "Indoors Readable File: ",
		            m_text = "This message is from Indoor Positioning App. Required file(s) are attached.";
		    Properties props = new Properties();
		    props.put("mail.smtp.user", d_email);
		    props.put("mail.smtp.host", d_host);
		    props.put("mail.smtp.port", d_port);
		    props.put("mail.smtp.starttls.enable","true");
		    props.put("mail.smtp.debug", "true");
		    props.put("mail.smtp.auth", "true");
		    props.put("mail.smtp.socketFactory.port", d_port);
		    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		    props.put("mail.smtp.socketFactory.fallback", "false");

		    Authenticator auth = new SMTPAuthenticator();
		    Session session = Session.getInstance(props, auth);
		    session.setDebug(true);

		    MimeMessage msg = new MimeMessage(session);
		    
		        msg.setSubject(m_subject);
		        msg.setFrom(new InternetAddress(d_email));
		        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(m_to));
		        msg.setText(m_text);

		Transport transport = session.getTransport("smtps");
		            transport.connect(d_host, Integer.valueOf(d_port), d_uname, d_password);
		            transport.sendMessage(msg, msg.getAllRecipients());
		            transport.close();
		            
		    
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		
	

}

class SMTPAuthenticator extends Authenticator {  
	  
	@Override  
	public PasswordAuthentication getPasswordAuthentication() {  
	String username = "manoj@traqbird.com";  
	String password = "Temp@1989";  
	return new PasswordAuthentication(username, password);  
	}  
	}  
