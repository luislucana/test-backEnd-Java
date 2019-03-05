package br.com.players.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.players.controller.util.PlayerGroupEnum;
import br.com.players.controller.vo.PlayerVO;
import br.com.players.persistence.model.Player;
import br.com.players.service.PlayerService;

@Controller
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping()
	public String pagina(PlayerVO playerVO) {
		return "pagina";
	}

	@RequestMapping({ "/validarAlgo" })
	public String validarAlgo(final PlayerVO playerVO) {
		System.out.println(playerVO.getNome());
		System.out.println(playerVO.getPlayerGroup().name());
		return "pagina";
	}

	@ModelAttribute("listarGrupos")
	public List<PlayerGroupEnum> listarGrupos() {
	    return Arrays.asList(PlayerGroupEnum.values());
	}
}
