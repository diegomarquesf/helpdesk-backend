package br.com.diegomarques.helpdesk.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.diegomarques.helpdesk.domain.enums.Perfil;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public abstract class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Geração ID Automatica
	protected Integer id;
	
	protected String nome;
	
	@CPF
	@Column(unique = true) // CPF unico no banco de dados
	protected String cpf;
	
	@Column(unique = true)
	protected String email;
	protected String senha;
	
	@ElementCollection(fetch = FetchType.EAGER) //Assegurar que essa lista virá Junto com o Usuário
	@CollectionTable(name = "PERFIS") //nome no banco de dados
	protected Set<Integer> perfis = new HashSet<>(); //instaciando a lista de Perfis
	
	@JsonFormat(pattern = "dd/MM/yyyy") //Formato da data
	protected LocalDate dataCriacao = LocalDate.now(); // Momento atual da criação
	
	
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE);  // Cada Pessoa Criada receberá perfil de Cliente.
	}


	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
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


	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());  //Mapeando cada Perfil e convertendo para tipo SET<>
	}


	public void addPerfil(Perfil perfil) {  //adicionando o código do perfil
		this.perfis.add(perfil.getCodigo()); 
	}


	public LocalDate getDataCriacao() {
		return dataCriacao;
	}


	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}


	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
	
	
	
	

}
