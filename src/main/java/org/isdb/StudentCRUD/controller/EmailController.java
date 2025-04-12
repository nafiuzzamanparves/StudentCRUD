package org.isdb.StudentCRUD.controller;

import jakarta.mail.MessagingException;
import org.isdb.StudentCRUD.model.EmailRequest;
import org.isdb.StudentCRUD.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
        try {
            emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
            return ResponseEntity.ok("Email sent successfully");
        } catch (MessagingException | IOException | GeneralSecurityException e) {
            return ResponseEntity.internalServerError().body("Failed to send email: " + e.getMessage());
        }
    }

    @PostMapping("/send-email-with-attachment")
    public ResponseEntity<String> sendEmailWithAttachment(
            @RequestParam("to") String to,
            @RequestParam("subject") String subject,
            @RequestParam("body") String body,
            @RequestParam("attachment") MultipartFile attachment) {
        try {
            emailService.sendEmailWithAttachment(to, subject, body, attachment);
            return ResponseEntity.ok("Email with attachment sent successfully");
        } catch (MessagingException | IOException | GeneralSecurityException e) {
            return ResponseEntity.internalServerError().body("Failed to send email: " + e.getMessage());
        }
    }
}
