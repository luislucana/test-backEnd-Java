package br.com.players.service.vo;

import br.com.players.service.util.ReferenceFileUtils;

/**
 * Representa o atributo 'vingadores' do conteudo do arquivo de referencia {@link ReferenceFileUtils.VINGADORES_URL_FILE}
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
public class VingadoresVO {

	private String codinome;

	public String getCodinome() {
		return codinome;
	}

	public void setCodinome(String codinome) {
		this.codinome = codinome;
	}
}
