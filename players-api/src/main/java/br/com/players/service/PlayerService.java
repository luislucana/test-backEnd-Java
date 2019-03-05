package br.com.players.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.players.persistence.model.Player;
import br.com.players.persistence.repository.PlayerRepository;

/**
 * 
 * 
 * @author Luis Lucana
 *
 */
@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;
	
	
}
