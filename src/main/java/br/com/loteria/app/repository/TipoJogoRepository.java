package br.com.loteria.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.loteria.app.entity.TipoJogo;

@Repository
public interface TipoJogoRepository extends JpaRepository<TipoJogo, Integer>{
	
	@Query("select tj from TipoJogo tj where nome = :nome")
	TipoJogo getOneByNome( @Param("nome") final String nome);
}
