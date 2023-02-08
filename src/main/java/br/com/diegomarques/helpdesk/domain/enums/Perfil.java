package br.com.diegomarques.helpdesk.domain.enums;

public enum Perfil {

	ADMIN(0,"ROLE_ADMIN"), CLIENTE(1,"ROLE_CLIENTE"), TECNICO(2,"ROLE_TECNICO");
	
	private Integer codigo;     	
	private String descricao;	
	
	private Perfil(Integer codigo, String descricao) {
		this.codigo = codigo;
		this.descricao = descricao;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static Perfil toEnum(Integer cod) {
		if(cod == null) { 			//Se o código for Nulo Retorna Nulo.
			return null;
		}
		for(Perfil x : Perfil.values()) {  	  		//Para cada Perfil da variavél X que for encontrado na nossa lista 
			if(cod.equals(x.getCodigo())) {			// e se códigco for igual ao da variavel X,
				return x;							//assim retornando o valor do código X.
			}
		}
		throw new IllegalArgumentException("Perfil Iválido");       //Mensagem de Exceção caso não entre em nenhum dos casos acima
	}
	
}
