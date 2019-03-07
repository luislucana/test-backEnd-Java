package br.com.players.persistence.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "player")
public class Player {

	@Id
	@SequenceGenerator(name = "seqGenerator", sequenceName = "PLAYER_SEQ", initialValue = 1)
	@GeneratedValue(generator = "seqGenerator")
	private Long id;

	private String nome;

	private String email;

	private String telefone;

	private String codinome;

	private Integer grupo;

	public Player() {
	}

	Player(String nome, String email, String telefone, String codinome,
			Integer grupo) {
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.codinome = codinome;
		this.grupo = grupo;
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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCodinome() {
		return codinome;
	}

	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}

	public Integer getGrupo() {
		return grupo;
	}

	public void setGrupo(Integer grupo) {
		this.grupo = grupo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}