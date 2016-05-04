package br.com.loteria.app.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.loteria.app.entity.Dezenas;

@Repository
public interface DezenasRepository extends JpaRepository<Dezenas, Integer> {
	
	@Query("select COUNT(d.numero), d.numero from dezenas d left join "
			+ " d.concurso c where c.dataSorteio between :first and :second group by d.numero")
	List<Object[]> countList(@Param("first") Date first, @Param("second") Date second);
}
