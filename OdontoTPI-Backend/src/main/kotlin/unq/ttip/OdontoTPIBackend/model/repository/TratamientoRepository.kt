package unq.ttip.OdontoTPIBackend.model.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.ttip.OdontoTPIBackend.model.dto.Tratamiento

@Repository
interface TratamientoRepository : CrudRepository<Tratamiento, Long> {
}