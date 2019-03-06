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
	
	public static PlayerGroupEnum valueOf(int number) {
		PlayerGroupEnum playerGroup = resolve(number);
		if (playerGroup == null) {
			throw new IllegalArgumentException("No matching constant for [" + String.valueOf(number) + "]");
		}
		return playerGroup;
	}
	
	public static PlayerGroupEnum resolve(int number) {
		for (PlayerGroupEnum playerGroup : values()) {
			if (playerGroup.number == number) {
				return playerGroup;
			}
		}
		return null;
	}
}
