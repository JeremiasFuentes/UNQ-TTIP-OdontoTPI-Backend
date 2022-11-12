package unq.ttip.OdontoTPIBackend.model.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.ttip.OdontoTPIBackend.model.dto.Paciente
import unq.ttip.OdontoTPIBackend.model.dto.Turno


@Repository
interface TurnoRepository : CrudRepository<Turno, Long> {
}