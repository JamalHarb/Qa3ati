package com.codingdojo.qa3ati.services;

import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.*;
import org.springframework.mail.SimpleMailMessage;

@Service
public class EmailSender {
	private JavaMailSender mailSender;
	
	public EmailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendEmail(String toEmail, String subject, String msg) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(""); // enter sender email address
		message.setTo(toEmail);
		message.setText(msg);
		message.setSubject(subject);
		mailSender.send(message);
		System.out.println("mail sent successfully ...");
	}
}
