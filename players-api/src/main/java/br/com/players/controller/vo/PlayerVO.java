package br.com.players.controller.vo;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.players.controller.util.PlayerGroupEnum;

/**
 * VO que armazena os dados informados no cadastro do jogador.
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
public class PlayerVO {

	private Long id;

	@NotNull(message = "Nome é obrigatório")
	@NotEmpty(message = "Nome é obrigatório")
	private String nome;

	@NotNull(message = "Email é obrigatório")
	@NotEmpty(message = "Email é obrigatório")
	private String email;

	private String telefone;

	@NotNull(message = "Selecione um grupo")
	private PlayerGroupEnum playerGroup;

	private String codinome;

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

	public PlayerGroupEnum getPlayerGroup() {
		return playerGroup;
	}

	public void setPlayerGroup(PlayerGroupEnum playerGroup) {
		this.playerGroup = playerGroup;
	}

	public String getCodinome() {
		return codinome;
	}

	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}
}