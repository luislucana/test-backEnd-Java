package br.com.players.service.vo;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import br.com.players.service.util.ReferenceFileUtils;

/**
 * Representa o conteudo do arquivo de referencia {@link ReferenceFileUtils.LIGA_JUSTICA_URL_FILE}
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
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
