package br.com.players.service.vo;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

@Root(name = "liga_da_justica", strict = false)
public class LigaJusticaVO {

	@ElementList(name = "codinomes", entry = "codinome")
	private List<String> codinomes;

	public List<String> getCodinomes() {
		return codinomes;
	}

	public void setCodinomes(List<String> codinomes) {
		this.codinomes = codinomes;
	}
}
