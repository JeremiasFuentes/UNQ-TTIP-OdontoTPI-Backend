package unq.ttip.OdontoTPIBackend.model.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.ttip.OdontoTPIBackend.model.dto.Paciente
import unq.ttip.OdontoTPIBackend.model.dto.PiezaDentaria
import unq.ttip.OdontoTPIBackend.model.repository.PacienteRepository
import unq.ttip.OdontoTPIBackend.model.repository.PiezaDentariaRepository
import java.util.*
import javax.transaction.Transactional

@Transactional
@Service
class PacienteService {

    @Autowired
    lateinit var pacienteRepository: PacienteRepository

    @Autowired
    lateinit var piezaDentariaRepository: PiezaDentariaRepository

    fun getAll() = pacienteRepository.findAll()
    fun save(paciente:Paciente): Paciente {
        var lista = mutableListOf<PiezaDentaria>()
        for (num in 1..16){
            var pieza = PiezaDentaria(num)
            pieza.setPaciente(paciente)
            lista.add(pieza)

        }
        paciente.piezas = (lista)
        return pacienteRepository.save(paciente)}
    fun getPaciente(id:Long) = pacienteRepository.findById(id)
    fun existPaciente(id:Long) = pacienteRepository.existsById(id)
    fun searchPacientes(filtro:String) : List<Paciente> { return pacienteRepository.searchPaciente(filtro)}

    fun delete(id:Long) = pacienteRepository.deleteById(id)
    fun getPiezas(id: Long): MutableList<PiezaDentaria>? {
        var paciente = this.getPaciente(id).get()
        var odont = paciente.getPiezasDentarias()
        return odont
    }


    @Transactional
    fun updatePiezas(id: Long, piezas: List<PiezaDentaria>): Paciente {

        var paciente = this.getPaciente(id).get()
        paciente.setPiezasDentaria(piezas as MutableList<PiezaDentaria>)
        for(p in piezas){
            p.setPaciente(paciente)
            piezaDentariaRepository.save(p)
        }
        return pacienteRepository.save(paciente)
    }

    @Transactional
    fun update(paciente: Paciente): Paciente {
        return pacienteRepository.save(paciente)
    }

}