package com.Metro.org.repository;

import com.Metro.org.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Integer> {
    List<Horario> findByIdTren(Integer idTren);

    @Query("select h from Horario h where h.idTren = :idTren and " +
            "((:nuevaSalida < h.horaLlegada and :nuevaLlegada > h.horaSalida))")
    List<Horario> buscarTraslapes(@Param("idTren") Integer idTren,
                                  @Param("nuevaSalida") LocalTime nuevaSalida,
                                  @Param("nuevaLlegada") LocalTime nuevaLlegada);
}