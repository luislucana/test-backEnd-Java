package br.com.players.controller.util;

/**
 * Enum para definir os grupos permitidos para cadastro.
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
public enum PlayerGroupEnum {
	
	LIGA_DA_JUSTICA(1, "Liga da Justiça"),
	
	VINGADORES(2, "Vingadores");
	
	private int number;
	
	private String label;
	
	PlayerGroupEnum(int number, String label) {
		this.number = number;
		this.label = label;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
