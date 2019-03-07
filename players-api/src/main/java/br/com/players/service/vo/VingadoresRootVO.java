package br.com.players.service.vo;

import java.util.List;

import br.com.players.service.util.ReferenceFileUtils;

/**
 * Representa o conteudo do arquivo de referencia {@link ReferenceFileUtils.VINGADORES_URL_FILE}
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
public class VingadoresRootVO {

	private List<VingadoresVO> vingadores;

	public List<VingadoresVO> getVingadores() {
		return vingadores;
	}

	public void setVingadores(List<VingadoresVO> vingadores) {
		this.vingadores = vingadores;	
	}
}
