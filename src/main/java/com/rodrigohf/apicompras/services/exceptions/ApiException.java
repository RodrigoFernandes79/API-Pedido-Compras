package com.rodrigohf.apicompras.services.exceptions;

public class ApiException {

	private Long localDate;
	private Integer status;
	private String erro;
	private String mensagem;
	private String path;
	
	

	public ApiException(Long localDateTime, Integer status, String erro, String mensagem, String path) {
		super();
		this.localDate = localDateTime;
		this.status = status;
		this.erro = erro;
		this.mensagem = mensagem;
		this.path = path;
	}

	

	public Long getLocalDate() {
		return localDate;
	}

	public String getMensagem() {
		return mensagem;
	}
	

	public Integer getStatus() {
		return status;
	}
	
	public String getErro() {
		return erro;
	}

	public void setErro(String erro) {
		this.erro = erro;
	}
	

	public String getPath() {
		return path;
	}

	
	
	
}
