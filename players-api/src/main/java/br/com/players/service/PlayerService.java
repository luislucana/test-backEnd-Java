package br.com.players.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.players.controller.util.PlayerGroupEnum;
import br.com.players.controller.vo.PlayerVO;
import br.com.players.persistence.model.Player;
import br.com.players.persistence.repository.PlayerRepository;
import br.com.players.service.util.ReferenceFileUtils;
import br.com.players.service.vo.LigaJusticaVO;
import br.com.players.service.vo.VingadoresRootVO;

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
	
	public PlayerVO createPlayer(PlayerVO playerVO) {
		
		if (playerVO == null) {
			throw new IllegalArgumentException("nenhum dado informado");
		}
		
		PlayerGroupEnum selectedPlayerGroup = playerVO.getPlayerGroup();
		
		if (selectedPlayerGroup == null) {
			throw new IllegalArgumentException("Deve ser selecionado um grupo.");
		}
		
		if (selectedPlayerGroup == PlayerGroupEnum.LIGA_DA_JUSTICA) {
			LigaJusticaVO allowedLigaJustica = ReferenceFileUtils.getAllowedLigaJustica();
		} else if (selectedPlayerGroup == PlayerGroupEnum.VINGADORES) {
			VingadoresRootVO allowedVingadores = ReferenceFileUtils.getAllowedVingadores();
		}
		
		PlayerVO savedPlayerVO = new PlayerVO();
		Player player = new Player();
		
		player.setNome(playerVO.getNome());
		// ...
		Player savedPlayer = playerRepository.save(player);
		
		savedPlayerVO.setId(savedPlayer.getId());
		savedPlayerVO.setNome(savedPlayer.getNome());
		savedPlayerVO.setEmail(savedPlayer.getEmail());
		savedPlayerVO.setTelefone(savedPlayer.getTelefone());
		savedPlayerVO.setPlayerGroup(PlayerGroupEnum.valueOf(savedPlayer.getGrupo()));
		
		return savedPlayerVO;
	}
	
	public List<PlayerVO> getAllPlayers() {
		Iterable<Player> allPlayers = playerRepository.findAll();
		List<PlayerVO> allPlayersVO = new ArrayList<PlayerVO>();
		
		if (allPlayers != null) {
			for (Player player : allPlayers) {
				PlayerVO playerVO = new PlayerVO();
				playerVO.setId(player.getId());
				playerVO.setNome(player.getNome());
				playerVO.setEmail(player.getEmail());
				playerVO.setTelefone(player.getTelefone());
				playerVO.setPlayerGroup(PlayerGroupEnum.valueOf(player.getGrupo()));
				
				allPlayersVO.add(playerVO);
			}
		}
		
		return allPlayersVO;
	}
	
	public void updatePlayer(PlayerVO playerVO, Long id) {
		playerRepository.save(new Player());
	}
	
	public void deletePlayer(Long id) {
		Player player = playerRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid player id:" + id));
		
	    playerRepository.delete(player);
	}
}
