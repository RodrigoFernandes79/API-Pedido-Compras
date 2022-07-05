package com.rodrigohf.apicompras.services.exceptions;

import java.time.LocalDateTime;

public class ApiException {

	private LocalDateTime localDate;
	private String mensagem;
	private Integer status;
	private String path;
	
	public ApiException(LocalDateTime localDate, String mensagem, Integer status, String path) {
		super();
		this.localDate = localDate;
		this.mensagem = mensagem;
		this.status = status;
		this.path = path;
	}

	public LocalDateTime getLocalDate() {
		return localDate;
	}

	public String getMensagem() {
		return mensagem;
	}

	public Integer getStatus() {
		return status;
	}

	public String getPath() {
		return path;
	}
	
	
}
