package br.com.loteria.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.loteria.app.entity.TipoJogo;

@Repository
public interface TipoJogoRepository extends JpaRepository<TipoJogo, Integer>{
	
}
