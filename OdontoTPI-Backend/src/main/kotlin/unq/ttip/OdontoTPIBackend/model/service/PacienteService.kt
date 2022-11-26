package unq.ttip.OdontoTPIBackend.model.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import unq.ttip.OdontoTPIBackend.model.dto.*
import unq.ttip.OdontoTPIBackend.model.repository.*
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

    @Autowired
    lateinit var archivosRepository: ArchivosRepository

    @Autowired
    lateinit var tratamientoRepository: TratamientoRepository

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

    @Transactional
    fun saveFile(id: Long, file:MultipartFile?): Archivo {
        var paciente = this.getPaciente(id).get()
        var archivos = paciente.getArchivosDePaciente()
        var downloadURL = ServletUriComponentsBuilder.fromCurrentContextPath().path("paciente/"+ id + "/" + file!!.getOriginalFilename()).toUriString()
        val fileData: Archivo = archivosRepository.save(
            Archivo(file?.getOriginalFilename()!!, file.getContentType()!!,downloadURL, file.getBytes(),paciente)
        )
        archivos!!.add(fileData)
        paciente.setArchivosDePaciente(archivos)
        pacienteRepository.save(paciente)
        return fileData
    }

    @Transactional
    fun getFile(id: Long, fileName: String?): Archivo? {
        var paciente = this.getPaciente(id).get()
        var archivos = paciente.getArchivosDePaciente()
        var archivo = archivos!!.find { a -> a.nombre == fileName }
        return archivo
    }
    @Transactional
    fun getFiles(id: Long): List<Archivo>? {
        var paciente = this.getPaciente(id).get()
        var archivos = paciente.getArchivosDePaciente()
        return archivos
    }

    fun saveTratamiento(id: Long, tratamiento: Tratamiento): Tratamiento {
        var paciente = this.getPaciente(id).get()
        var tratamientos = paciente.getTratamientosDePaciente()
        val fileData: Tratamiento = tratamientoRepository.save(
            Tratamiento(tratamiento.fecha, tratamiento.tratamiento ,tratamiento.firma ,paciente)
        )
        tratamientos!!.add(fileData)
        paciente.setTratamientosDePaciente(tratamientos)
        pacienteRepository.save(paciente)
        return fileData
    }

    fun getTratamientos(id: Long): List<Tratamiento>? {
        var paciente = this.getPaciente(id).get()
        var tratamientos = paciente.getTratamientosDePaciente()
        return tratamientos
    }

}