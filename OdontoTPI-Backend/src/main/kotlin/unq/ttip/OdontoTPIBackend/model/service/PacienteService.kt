package unq.ttip.OdontoTPIBackend.model.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.ttip.OdontoTPIBackend.model.dto.HistoriaClinica
import unq.ttip.OdontoTPIBackend.model.dto.Paciente
import unq.ttip.OdontoTPIBackend.model.dto.PiezaDentaria
import unq.ttip.OdontoTPIBackend.model.dto.Turno
import unq.ttip.OdontoTPIBackend.model.repository.PacienteRepository
import unq.ttip.OdontoTPIBackend.model.repository.PiezaDentariaRepository
import unq.ttip.OdontoTPIBackend.model.repository.TurnoRepository
import java.util.*
import javax.transaction.Transactional

@Transactional
@Service
class PacienteService {

    @Autowired
    lateinit var pacienteRepository: PacienteRepository

    @Autowired
    lateinit var piezaDentariaRepository: PiezaDentariaRepository

    @Autowired
    lateinit var turnoRepository: TurnoRepository

    fun getAll() = pacienteRepository.findAll()
    fun save(paciente:Paciente): Paciente {
        var lista = mutableListOf<PiezaDentaria>()
        for (num in 11..85){
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
        var paciente2 = this.getPaciente(paciente.Legajo!!).get()
        var piezas = paciente2.getPiezasDentarias()
        paciente.setPiezasDentaria(piezas as MutableList<PiezaDentaria>)
        for(p in piezas){
            p.setPaciente(paciente)
            piezaDentariaRepository.save(p)
        }
        return pacienteRepository.save(paciente)
    }

    fun updateHistoria(id: Long, historia: HistoriaClinica): Paciente {
        var paciente = this.getPaciente(id).get()
        paciente.setHistoriaClinca(historia)
        return pacienteRepository.save(paciente)
    }

    fun getHistoria(id: Long): HistoriaClinica {
        var paciente = this.getPaciente(id).get()
        var hist = paciente.getHistoria()
        return hist
    }

    fun getTurnos(id: Long): MutableList<Turno>? {
        var paciente = this.getPaciente(id).get()
        var turnos = paciente.getTurnosDePaciente()
        return turnos
    }

    fun saveTurno(id: Long, turno: Turno): Paciente? {
        var paciente = this.getPaciente(id).get()
        var turnos = paciente.getTurnosDePaciente()
        turnos!!.add(turno)
        turno.setPaciente(paciente)
        paciente.turnos = turnos
        return pacienteRepository.save(paciente)
    }

}