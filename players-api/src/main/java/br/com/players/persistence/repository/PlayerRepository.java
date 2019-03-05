package br.com.players.persistence.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.players.persistence.model.Player;

/**
 * Repository da entidade Player
 * 
 * @author Luis Lucana
 *
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

	Player findByNome(String nome);
}