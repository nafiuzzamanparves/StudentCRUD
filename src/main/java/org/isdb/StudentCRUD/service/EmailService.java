package org.isdb.StudentCRUD.service;

import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;
import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Multipart;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;
import org.isdb.StudentCRUD.utils.GmailServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Base64;
import java.util.Objects;
import java.util.Properties;

@Service
public class EmailService {

    @Autowired
    private GmailServiceUtil gmailServiceUtil;

    public void sendEmail(String to, String subject, String bodyText)
            throws MessagingException, IOException, GeneralSecurityException {
        // Get Gmail service
        Gmail service = gmailServiceUtil.getGmailService();

        // Create email content
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress("me")); // "me" is a special value for authorized user
        email.addRecipient(RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);
        email.setText(bodyText);

        // Encode and send the email
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.getEncoder().encodeToString(bytes);

        Message message = new Message();
        message.setRaw(encodedEmail);

        // Send the message
        service.users().messages().send("me", message).execute();
    }

    public void sendEmailWithAttachment(String to, String subject, String bodyText, MultipartFile attachment)
            throws MessagingException, IOException, GeneralSecurityException {
        // Get Gmail service
        Gmail service = gmailServiceUtil.getGmailService();

        // Create email content
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);

        email.setFrom(new InternetAddress("me")); // "me" is a special value for authorized user
        email.addRecipient(RecipientType.TO, new InternetAddress(to));
        email.setSubject(subject);

        // Create multipart message
        Multipart multipart = new MimeMultipart();

        // Text part
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setText(bodyText);
        multipart.addBodyPart(textPart);

        // Attachment part
        MimeBodyPart attachmentPart = new MimeBodyPart();
        // Convert MultipartFile to File
        File convFile = convertMultiPartToFile(attachment);
        DataSource source = new FileDataSource(convFile);
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(attachment.getOriginalFilename());
        multipart.addBodyPart(attachmentPart);

        // Set the content
        email.setContent(multipart);

        // Encode and send the email
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] bytes = buffer.toByteArray();
        String encodedEmail = Base64.getEncoder().encodeToString(bytes);
        Message message = new Message();
        message.setRaw(encodedEmail);

        // Send the message
        service.users().messages().send("me", message).execute();

        // Delete the temporary file
        convFile.delete();
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}