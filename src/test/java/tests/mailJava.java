package tests;

//import org.seleniumhq.jetty9.server.session.Session;

//import java.net.PasswordAuthentication;
/*import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.mail.Session;

public class mailJava {



    public static void main(String[] args) {
        final String username = "samuel.janoska@accenture.com";  // like yourname@outlook.com
        final String password = "aniSpravneNefungovalo";   // password here

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp-mail.outlook.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        session.setDebug(true);


        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("samuel.janoska@accenture.com"));   // like inzi769@gmail.com
            message.setSubject("Test");
            message.setText("HI you have done sending mail with outlook");

            //Transport.send(message);
            message.setFlag(Flags.Flag.DRAFT, true);

            System.out.println("Donesss");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
*/

import java.util.*;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.Store;

public class mailJava {

    public static void main(String[] args) throws MessagingException {
        String to =   "samuel.janoska@accenture.com";
        String from = "samuel.janoska@accenture.com";
        String host = "accenture.com";

        Properties properties = System.getProperties();

        properties.setProperty("mail.smtp.host", host);

        //Session session = Session.getDefaultInstance(properties);

        Message message = new MimeMessage(session);

        message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(to));

        message.setSubject("This is the Subject Linessss!");

        BodyPart messageBodyPart = new MimeBodyPart();

        messageBodyPart.setText("This is message bodysssss");

        // Create a multipar message
        Multipart multipart = new MimeMultipart();

        // Set text message part
        multipart.addBodyPart(messageBodyPart);

        // Part two is attachment
        /*messageBodyPart = new MimeBodyPart();

        String filename = "C:/temp/bb.log";
        FileDataSource source = new FileDataSource(filename);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(filename);
        multipart.addBodyPart(messageBodyPart);*/

        // Send the complete message parts
        message.setContent(multipart);

        properties.setProperty("mail.store.protocol", "imaps");
        properties.setProperty("mail.imaps.port", "993");

        /*session = Session.getDefaultInstance(properties, null);

        javax.mail.Store store = session.getStore("imaps");*/

// I need to add my system login and password to access IMAP host, you can check with your admin

  //        store.connect("outlook.accenture.com", "XXXXX", "XXXXXX");
        //ked je accenture.com tak je len Connection timed out: connect error, inak aj specifickejsie erory. takze bude problem v mojej autentifikacii
        //store.connect("accenture.com", "samuel.janoska", "mh44D3jR");
        Session session = Session.getInstance(properties, null);
        Store store = session.getStore(getProtocol());
        store.connect(getIncomingHost(), getUsername(), getPassword());

        Folder draft = store.getFolder("drafts");

        draft.open(Folder.READ_WRITE);

        draft.appendMessages(new Message[] { message });
        System.out.println(
                draft.getFullName()
                        + " has : "
                        + draft.getMessageCount()
                        + " Message(s)");

    }
}