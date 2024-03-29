package com.rodrigohf.apicompras.dtos;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.rodrigohf.apicompras.services.validations.ClienteInsert;

//Classe DTO para inserir um cliente com endereço e telefones

@ClienteInsert // anotação personalizada criada para validação de CPF ou CNPJ
public class ClienteNewDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	@Length(min=5, max=120, message="O campo deve ser preenchido entre 5 e 120 caracteres!")
	private String nome;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	@Email(message="Email inválido!")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String senha;
	

	@NotEmpty(message="Preenchimento obrigatório!")
	@Length(min=11, max=14, message="O campo deve ser preenchido entre 11 e 14 caracteres!")
	private String cpfOuCnpj;
	
	
	private Integer tipo;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String logradouro;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String cep;
	
	private Long cidadeId;

	public ClienteNewDTO() {
		
	}

	public ClienteNewDTO(String nome, String email, String cpfOuCnpj, Integer tipo, String telefone1, String telefone2,
			String telefone3, String logradouro, String numero, String complemento, String bairro, String cep,
			Long cidadeId, String senha) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipo = tipo;
		this.telefone1 = telefone1;
		this.telefone2 = telefone2;
		this.telefone3 = telefone3;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidadeId = cidadeId;
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public String getTelefone1() {
		return telefone1;
	}

	public void setTelefone1(String telefone1) {
		this.telefone1 = telefone1;
	}

	public String getTelefone2() {
		return telefone2;
	}

	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
	}

	public String getTelefone3() {
		return telefone3;
	}

	public void setTelefone3(String telefone3) {
		this.telefone3 = telefone3;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Long getCidadeId() {
		return cidadeId;
	}

	public void setCidadeId(Long cidadeId) {
		this.cidadeId = cidadeId;
	}
	
	
	

}
