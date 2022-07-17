package com.rodrigohf.apicompras.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.rodrigohf.apicompras.services.emailServices.AbstractEmailService;

public class SmtpEmailService extends AbstractEmailService{

	@Autowired
	private MailSender mailSender;  // implementa as configuraões do email do application-dev properties
	
	private static Logger lg = LoggerFactory.getLogger(SmtpEmailService.class); // para aparecer os dados no log do console

	@Override
	public void enviarEmail(SimpleMailMessage msg) {
		lg.info("Enviando email...");
		mailSender.send(msg);
		lg.info("Email enviado!");
		
	}

	
	
	
	
	

}
