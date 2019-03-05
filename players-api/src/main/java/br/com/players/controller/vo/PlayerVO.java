package br.com.players.controller.vo;

import br.com.players.controller.util.PlayerGroupEnum;

/**
 * VO que armazena os dados informados no cadastro do jogador.
 * 
 * @author Luis Lucana
 *
 */
public class PlayerVO {
	
	private String nome;
	
	private PlayerGroupEnum playerGroup;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public PlayerGroupEnum getPlayerGroup() {
		return playerGroup;
	}

	public void setPlayerGroup(PlayerGroupEnum playerGroup) {
		this.playerGroup = playerGroup;
	}
}
