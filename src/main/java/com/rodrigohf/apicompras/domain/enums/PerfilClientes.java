package com.rodrigohf.apicompras.domain.enums;

public enum PerfilClientes {
	
	ADMIN(1,"ROLE_ADMIN"),
	CLIENTE(2,"ROLE_CLIENTE");
	
	

	private Integer cod;
	private String descricao;
	
	
	private PerfilClientes(Integer cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}


	public int getCod() {
		return cod;
	}


	public String getDescricao() {
		return descricao;
	}
	
	
	public static PerfilClientes toEnum(Integer cod) {
		
		if(cod== null) {
			return null;
		}
		
		for (PerfilClientes x : PerfilClientes.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Código "+ cod + " Informado não encontrado");
		
	}

}
