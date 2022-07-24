package com.rodrigohf.apicompras.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.rodrigohf.apicompras.domain.Cliente;
import com.rodrigohf.apicompras.domain.enums.TipoCliente;
import com.rodrigohf.apicompras.dtos.ClienteNewDTO;
import com.rodrigohf.apicompras.repositories.ClienteRepository;
import com.rodrigohf.apicompras.services.exceptions.ApiException;
import com.rodrigohf.apicompras.services.validations.utils.BR;

//classe que cria o validator personalizado para CPF ou CNPJ de ClienteNewDTO
public class CLienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDto, ConstraintValidatorContext context) {
		List<ApiException> list = new ArrayList<>();

		// inclua os testes aqui, inserindo erros na lista

		// se o tipo do objDto for igual ao codigo de pessoa fisica ,e o cpf do
		// objDto(ClienteNewDTO) não for válido:
		if (objDto.getTipo().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(objDto.getCpfOuCnpj())) {

			list.add(new ApiException(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Campo CPF inválido!",
					null, null));
		}

		// se o tipo do objDto for igual ao codigo de pessoa jurídica ,e o cnpj do
		// objDto(ClienteNewDTO) não for válido:
		if (objDto.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {

			list.add(new ApiException(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
					"Campo CNPJ inválido!", null, null));
		}

		// Validar se já existe email no Banco de dados:
		Cliente find = clienteRepository.findByEmail(objDto.getEmail());
		if (find != null) { // se o email já existe no BD:
			list.add(new ApiException(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
					"Email " + objDto.getEmail() + " já existe", null, null));
		}

		for (ApiException e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addConstraintViolation();
		}
		return list.isEmpty();
	}
}