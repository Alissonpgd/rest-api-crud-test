package com.crud.accenture;

import java.time.LocalDate;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;

import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "fornecedor")
@Data

public class Fornecedor {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
	
	@Column(name = "nome")
    @JsonProperty("nome")
	private String nome;
	
	@Column(unique = true)
    @JsonProperty("cnpj_cpf")
	private String cnpj_cpf;
	
	@Column(name = "rg")
    @JsonProperty("rg")
	private String rg;
	
	@Column(name = "data_nascimento")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate data_nascimento;
	
	@Column(name = "email")
    @JsonProperty("email")
	private String email;
	
	@Column(name = "cep")
    @JsonProperty("cep")
	private String cep;

	@ManyToOne
	@JoinColumn(name = "empresa_id")
	private Empresa empresa;

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	// Definindo o enum TipoPessoa
	public enum TipoPessoa {
		FISICA, JURIDICA;
	}

	@Column(name = "tipo_pessoa")
	@Enumerated(EnumType.STRING)
	private TipoPessoa tipoPessoa;
	
	
//	getter and setters	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj_cpf() {
		return cnpj_cpf;
	}

	public void setCnpj_cpf(String cnpj_cpf) {
		this.cnpj_cpf = cnpj_cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}
	
	//  Constructor
	
	public Fornecedor(Long id, String nome, String cnpj_cpf, String rg, LocalDate data_nascimento, String email,
			String cep, TipoPessoa tipoPessoa) {
		super();
		this.id = id;
		this.nome = nome;
		this.cnpj_cpf = cnpj_cpf;
		this.rg = rg;
		this.data_nascimento = data_nascimento;
		this.email = email;
		this.cep = cep;
		this.tipoPessoa = tipoPessoa;
	} 
	

	public Fornecedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean isPessoaFisica() {
		return TipoPessoa.FISICA.equals(this.tipoPessoa);
	}

}



