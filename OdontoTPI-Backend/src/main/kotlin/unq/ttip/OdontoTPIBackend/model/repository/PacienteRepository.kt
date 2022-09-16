package unq.ttip.OdontoTPIBackend.model.repository

import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import unq.ttip.OdontoTPIBackend.model.dto.Paciente

@Repository
interface PacienteRepository: CrudRepository<Paciente,Long>{

    @Query(value = "SELECT p FROM Paciente p WHERE p.nombre LIKE %:filtro% OR p.apellido LIKE %:filtro% OR p.dni LIKE %:filtro%")
    fun searchPaciente(@Param("filtro") filtro:String) : List<Paciente>

}