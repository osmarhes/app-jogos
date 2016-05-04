package br.com.loteria.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.loteria.app.entity.Concursos;

@Repository
public interface ConcursosRepository extends JpaRepository<Concursos, Integer> {

	@Query("select c from concursos c where c.dataSorteio = :dataSorteio")
	List<Concursos> getByDate(@Param("dataSorteio")Date dataSorteio);
	
	@Query("select c from concursos c order by id desc")
	List<Concursos> lista();
}
