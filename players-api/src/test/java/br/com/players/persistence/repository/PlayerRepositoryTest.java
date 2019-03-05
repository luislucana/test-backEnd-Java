package br.com.players.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;

public class PlayerRepositoryTest {

	@Autowired
	private PlayerRepository playerRepository;
	
	//@Test
    public void whenFindingCustomerById_thenCorrect() {
        //playerRepository.save(new Player("John", "john@domain.com"));
        //assertThat(playerRepository.findById(1L)).isInstanceOf(Optional.class);
    }
}
