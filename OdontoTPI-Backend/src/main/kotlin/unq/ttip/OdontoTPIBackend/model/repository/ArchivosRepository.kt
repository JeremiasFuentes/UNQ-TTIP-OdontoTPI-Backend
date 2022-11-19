package unq.ttip.OdontoTPIBackend.model.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import unq.ttip.OdontoTPIBackend.model.dto.Archivo
import unq.ttip.OdontoTPIBackend.model.dto.Paciente
import java.util.Optional

@Repository
interface ArchivosRepository: CrudRepository<Archivo, Long> {

    fun findByNombre(nombre: String) : Optional<Archivo>
}