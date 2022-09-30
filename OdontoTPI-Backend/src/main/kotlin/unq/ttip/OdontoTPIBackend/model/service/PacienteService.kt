package unq.ttip.OdontoTPIBackend.model.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.ttip.OdontoTPIBackend.model.dto.Paciente
import unq.ttip.OdontoTPIBackend.model.repository.PacienteRepository
import javax.transaction.Transactional

@Transactional
@Service
class PacienteService {

    @Autowired
    lateinit var pacienteRepository: PacienteRepository

    fun getAll() = pacienteRepository.findAll()
    fun save(paciente:Paciente): Paciente { return pacienteRepository.save(paciente)}
    fun getPaciente(id:Long) = pacienteRepository.findById(id)
    fun existPaciente(id:Long) = pacienteRepository.existsById(id)
    fun searchPacientes(filtro:String) : List<Paciente> { return pacienteRepository.searchPaciente(filtro)}

    fun delete(id:Long) = pacienteRepository.deleteById(id)

}