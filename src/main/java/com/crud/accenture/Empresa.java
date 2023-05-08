package com.crud.accenture;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author alisson.r.silva
 *
 */
@Getter
@Setter
@Entity
@Table(name = "empresa")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Empresa {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(name = "nome_fantasia")
    @JsonProperty("nome_fantasia")
	private String nomeFantasia;
	
	@Column(name="cnpj")
	@JsonProperty("cnpj")
	private String cnpj;
	
	@Column(name="cep")
	@JsonProperty("cep")
	private String cep;

	@Column(name="estado")
	@JsonProperty("estado")
	private String estado;


	//Uma Empresa pode ter mais de um Fornecedor

	@OneToMany(mappedBy = "empresa")
	private List<Fornecedor> fornecedores;
	
	//	getters and setters
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
}
