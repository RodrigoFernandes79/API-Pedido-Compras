package com.rodrigohf.apicompras.services.emailServices;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.domain.Pedido;

//Classe de implementação dos metodos da interface EmailService

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void emailDeConfirmaçãoDoPedido(Pedido pedido) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(pedido);
		
		
		
		  enviarEmail(sm);
	}

	

	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido) {
		
		SimpleMailMessage sm = new SimpleMailMessage();
		
		
		sm.setTo(pedido.getCliente().getEmail()); // destinatario
		sm.setFrom(sender); //email do remetente
		sm.setSubject("Pedido Confirmado! Código: "+ pedido.getId());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(pedido.toString()); // corpo do email (toString de pedido)
		return sm;
	}

	//implementaçao do metodo que envia a nova senha , caso o cliente tenha esquecido;
	@Override
	public void sendNewPasswordEmail(Cliente cliente, String newPass) {
		SimpleMailMessage sm = prepareNewPasswordEmail(cliente,newPass);
		
		enviarEmail(sm);
		
	}



	protected  SimpleMailMessage prepareNewPasswordEmail(Cliente cliente, String newPass) {
	SimpleMailMessage sm = new SimpleMailMessage();
	sm.setTo(cliente.getEmail()); // destinatario
	sm.setFrom(sender); //email do remetente
	sm.setSubject("Solicitação de nova senha");
	sm.setSentDate(new Date(System.currentTimeMillis()));
	sm.setText("Nova Senha " +newPass); // corpo do email (toString de pedido)
	return sm;
	}

}
