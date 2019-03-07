package br.com.players.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.players.persistence.model.Player;

/**
 * Repository da entidade Player.
 * 
 * @author Luis Lucana (luislucana@gmail.com)
 *
 */
@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {

	Player findByNome(String nome);
	
	@Query("SELECT player.codinome FROM Player player WHERE player.grupo = :grupo")
	List<String> findCodinomesByGrupo(@Param("grupo") Integer grupo);
}