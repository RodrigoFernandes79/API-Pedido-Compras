package com.rodrigohf.apicompras.services.emailServices;




import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.domain.Pedido;

// interface que cria um contrato onde é implementada as operaçoes que um servico de email deve possuir
@Component
public interface EmailService {

	 void emailDeConfirmaçãoDoPedido(Pedido pedido);
	
	 void enviarEmail(SimpleMailMessage msg);
	 
	 //acrescentar uma nova senha, para o Esqueci a senha
	void sendNewPasswordEmail(Cliente cliente, String newPass);
}
