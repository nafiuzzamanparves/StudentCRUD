package org.isdb.StudentCRUD.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Properties;

import com.google.common.io.BaseEncoding;
import org.isdb.StudentCRUD.utils.GmailServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.util.Base64;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Message;

import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

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
		String encodedEmail = Base64.encodeBase64URLSafeString(bytes);

		Message message = new Message();
		message.setRaw(encodedEmail);

		// Send the message
		service.users().messages().send("me", message).execute();
	}
}