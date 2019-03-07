package br.com.players.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.players.controller.util.PlayerGroupEnum;
import br.com.players.controller.vo.PlayerVO;
import br.com.players.service.PlayerService;

/**
 * Classe controller para a interface de cadastro de jogadores.
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@Controller
public class PlayerController {
	
	@Autowired
	private PlayerService playerService;
	
	@GetMapping("/pagina")
	public String pagina(PlayerVO playerVO) {
		return "pagina";
	}

	@ModelAttribute("listarGrupos")
	public List<PlayerGroupEnum> listarGrupos() {
	    return Arrays.asList(PlayerGroupEnum.values());
	}
	
	@GetMapping("/")
	public String preCreatePlayer(PlayerVO playerVO) {
		return "cadastrarJogador";
	}
	
	@PostMapping("/cadastrarJogador")
	public String createPlayer(@ModelAttribute("playerVO") PlayerVO playerVO) {
		System.out.println(playerVO.getEmail());
		System.out.println(playerVO.getNome());
		System.out.println(playerVO.getTelefone());
		System.out.println(playerVO.getPlayerGroup()!= null ? playerVO.getPlayerGroup().name() : "null");
		
		playerService.createPlayer(playerVO);
		
		return "cadastrarJogador";
	}
	
	@GetMapping("/listarJogadores")
	public String listarJogadores(Model model) {
		
		List<PlayerVO> listaJogadores = playerService.getAllPlayers();
		
		model.addAttribute("players", listaJogadores);
		
		return "listarJogadores";
	}
	
	@PutMapping("/{id}")
    public void updatePlayer(@RequestBody PlayerVO playerVO, @PathVariable Long id) {
        if (playerVO.getId() != id) {
          throw new RuntimeException();
        }
        
        //bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        //return bookRepository.save(book);
    }
	
	@DeleteMapping("/{id}")
    public void deletePlayer(@PathVariable Long id) {
        //bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        //bookRepository.deleteById(id);
    }
}
