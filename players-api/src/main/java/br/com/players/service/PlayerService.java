package br.com.players.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.players.controller.util.PlayerGroupEnum;
import br.com.players.controller.vo.PlayerVO;
import br.com.players.persistence.model.Player;
import br.com.players.persistence.repository.PlayerRepository;
import br.com.players.service.util.ReferenceFileUtils;
import br.com.players.service.vo.LigaJusticaVO;
import br.com.players.service.vo.VingadoresRootVO;
import br.com.players.service.vo.VingadoresVO;

/**
 * Classe de servico para a entidade Player
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	@Transactional
	public PlayerVO createPlayer(PlayerVO playerVO) {
		
		if (playerVO == null) {
			throw new IllegalArgumentException("Nenhum dado foi informado.");
		}
		
		PlayerGroupEnum grupoSelecionado = playerVO.getPlayerGroup();
		
		if (grupoSelecionado == null) {
			throw new IllegalArgumentException("Deve ser selecionado um grupo.");
		}
		
		List<String> codinomesDisponiveisList = obterCodinomesDisponiveis(grupoSelecionado);
		
		if (codinomesDisponiveisList == null || codinomesDisponiveisList.isEmpty()) {
			throw new RuntimeException("Não há mais codinomes disponíveis para o grupo selecionado.");
		}
		
		
		Player player = new Player();
		player.setNome(playerVO.getNome());
		player.setCodinome(codinomesDisponiveisList.get(0));
		player.setEmail(playerVO.getEmail());
		player.setTelefone(playerVO.getTelefone());
		player.setGrupo(grupoSelecionado.getNumber());
		
		Player savedPlayer = playerRepository.save(player);
		
		PlayerVO savedPlayerVO = new PlayerVO();
		savedPlayerVO.setId(savedPlayer.getId());
		savedPlayerVO.setNome(savedPlayer.getNome());
		savedPlayerVO.setEmail(savedPlayer.getEmail());
		savedPlayerVO.setTelefone(savedPlayer.getTelefone());
		savedPlayerVO.setPlayerGroup(PlayerGroupEnum.valueOf(savedPlayer.getGrupo()));
		
		return savedPlayerVO;
	}
	
	public List<PlayerVO> getAllPlayers() {
		Iterable<Player> allPlayers = playerRepository.findAll();
		List<PlayerVO> playerVOList = new ArrayList<PlayerVO>();
		
		if (allPlayers != null) {
			for (Player player : allPlayers) {
				PlayerVO playerVO = new PlayerVO();
				playerVO.setId(player.getId());
				playerVO.setNome(player.getNome());
				playerVO.setEmail(player.getEmail());
				playerVO.setTelefone(player.getTelefone());
				playerVO.setPlayerGroup(PlayerGroupEnum.valueOf(player.getGrupo()));
				
				playerVOList.add(playerVO);
			}
		}
		
		return playerVOList;
	}
	
	@Transactional
	public void updatePlayer(PlayerVO playerVO, Long id) {
		
		
		playerRepository.save(new Player());
	}
	
	@Transactional
	public void deletePlayer(Long id) {
		Player player = playerRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Identificador inválido de jogador:" + id));
		
	    playerRepository.delete(player);
	}
	
	private List<String> obterCodinomesDisponiveis(PlayerGroupEnum playerGroup) {
		
		List<String> todosCodinomesList = new ArrayList<String>();
		List<String> codinomesDisponiveisList = new ArrayList<String>();

		if (playerGroup == PlayerGroupEnum.LIGA_DA_JUSTICA) {
			LigaJusticaVO allowedLigaJustica = ReferenceFileUtils.getAllowedLigaJustica();
			
			if (allowedLigaJustica != null) {
				todosCodinomesList = allowedLigaJustica.getCodinomes();
			}
		} else if (playerGroup == PlayerGroupEnum.VINGADORES) {
			VingadoresRootVO allowedVingadores = ReferenceFileUtils.getAllowedVingadores();
			
			if (allowedVingadores != null) {
				List<VingadoresVO> vingadoresVOList = allowedVingadores.getVingadores();
				
				if (vingadoresVOList != null && !vingadoresVOList.isEmpty()) {
					for (VingadoresVO vingadoresVO : vingadoresVOList) {
						todosCodinomesList.add(vingadoresVO.getCodinome());
					}
				}
			}
		}
		
		List<String> codinomesUsadosList = playerRepository.findCodinomesByGrupo(playerGroup.getNumber());
		
		if (codinomesUsadosList != null && !codinomesUsadosList.isEmpty()) {
			todosCodinomesList.removeAll(codinomesUsadosList);
		}
		
		codinomesDisponiveisList = todosCodinomesList;
		
		return codinomesDisponiveisList;
	}
}
