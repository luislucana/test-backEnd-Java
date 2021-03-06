package br.com.players.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@GetMapping({"/", "/preCadastrarJogador"})
	public String preCreatePlayer(PlayerVO playerVO) {
		return "cadastrarJogador";
	}
	
	@PostMapping("/cadastrarJogador")
	public String createPlayer(@Valid PlayerVO playerVO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		
		if (!bindingResult.hasErrors()) {
			try {
				playerService.createPlayer(playerVO);
				
				redirectAttributes.addFlashAttribute("messageType", "sucesso");
				redirectAttributes.addFlashAttribute("message", "Sucesso!");
				redirectAttributes.addFlashAttribute("detailMessage", "Jogador cadastrado");
			} catch (Exception exception) {
				redirectAttributes.addFlashAttribute("playerVO", playerVO);
				redirectAttributes.addFlashAttribute("messageType", "erro");
				redirectAttributes.addFlashAttribute("message", "Erro!");
				redirectAttributes.addFlashAttribute("detailMessage", exception.getMessage());
			}
		} else {
			return "cadastrarJogador";
		}
		
		return "redirect:/preCadastrarJogador";
	}
	
	@RequestMapping("/listarJogadores")
	public String listarJogadores(Model model) {
		
		List<PlayerVO> listaJogadores = playerService.getAllPlayers();
		
		model.addAttribute("players", listaJogadores);
		
		return "listarJogadores";
	}
	
	@PostMapping("/pre-alterar/{id}")
    public String preUpdatePlayer(@PathVariable Long id, RedirectAttributes redirectAttributes) {
		
		PlayerVO playerVO = playerService.getPlayer(id);
		
        redirectAttributes.addFlashAttribute("playerVO", playerVO);
        
        return "redirect:/preCadastrarJogador";
    }
	
	@PostMapping("/alterar/{id}")
    public String updatePlayer(@Valid PlayerVO playerVO, BindingResult bindingResult, RedirectAttributes redirectAttributes, @PathVariable Long id) {
        
		if (!bindingResult.hasErrors()) {
			try {
				playerService.updatePlayer(playerVO, id);
	        
				redirectAttributes.addFlashAttribute("playerVO", new PlayerVO());
		        redirectAttributes.addFlashAttribute("messageType", "sucesso");
				redirectAttributes.addFlashAttribute("message", "Sucesso!");
				redirectAttributes.addFlashAttribute("detailMessage", "Dados do jogador alterados.");
			} catch (Exception exception) {
				redirectAttributes.addFlashAttribute("playerVO", playerVO);
				redirectAttributes.addFlashAttribute("messageType", "erro");
				redirectAttributes.addFlashAttribute("message", "Erro!");
				redirectAttributes.addFlashAttribute("detailMessage", exception.getMessage());
				return "redirect:/preCadastrarJogador";
			}
		} else {
			return "cadastrarJogador";
		}
        
		return "redirect:/preCadastrarJogador";
    }
	
	@PostMapping("/delete/{id}")
    public String deletePlayer(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
		try {
			playerService.deletePlayer(id);
			
			redirectAttributes.addFlashAttribute("messageType", "sucesso");
			redirectAttributes.addFlashAttribute("message", "Sucesso!");
			redirectAttributes.addFlashAttribute("detailMessage", "Jogador removido.");
		} catch (Exception exception) {
			redirectAttributes.addFlashAttribute("messageType", "erro");
			redirectAttributes.addFlashAttribute("message", "Erro!");
			redirectAttributes.addFlashAttribute("detailMessage", exception.getMessage());
		}
		
		return "redirect:/listarJogadores";
    }
}
