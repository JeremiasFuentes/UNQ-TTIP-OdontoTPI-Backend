package unq.ttip.OdontoTPIBackend.model.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.Resource
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import unq.ttip.OdontoTPIBackend.model.dto.*
import unq.ttip.OdontoTPIBackend.model.model.RespondeDataFile
import unq.ttip.OdontoTPIBackend.model.service.PacienteService
import unq.ttip.OdontoTPIBackend.model.service.TurnoService
import java.io.IOException
import java.time.LocalDate


@RestController
@CrossOrigin
@RequestMapping("paciente")
class PacienteController {

    @Autowired
    lateinit var pacienteService: PacienteService

    @Autowired
    lateinit var turnoService: TurnoService

    @GetMapping
    fun pacientes() = ResponseEntity.ok(pacienteService.getAll())

    @GetMapping("/turnos")
    fun turnos() = ResponseEntity.ok(turnoService.getAll())

    @GetMapping("/{id}/turnos")
    fun getTurnos(@PathVariable id: Long) = ResponseEntity.ok(pacienteService.getTurnos(id))

    @PostMapping("/{id}/turno/save")
    fun saveTurno(@PathVariable id:Long,@RequestBody turno: Turno): ResponseEntity<Paciente> {
        return ResponseEntity.ok(pacienteService.saveTurno(id,turno))
    }


    @PutMapping("/{id}/diag")
    fun addDiagostico(@PathVariable id:Long, @RequestBody diagnostico:String): Paciente {
        var paciente = pacienteService.getPaciente(id).get()
        var diagnosticos = paciente.getDiags()
        diagnosticos!!.add(diagnostico)
        paciente.setDiags(diagnosticos)
        return pacienteService.update(paciente)
    }

    @PutMapping("/{id}/odontograma")
    fun updatePiezas(@PathVariable id:Long, @RequestBody piezas: List<PiezaDentaria>): Paciente {

        return pacienteService.updatePiezas(id,piezas)
    }

    @PutMapping("/{id}/historiaClinica")
    fun updateHistoria(@PathVariable id:Long, @RequestBody historia: HistoriaClinica): Paciente {

        return pacienteService.updateHistoria(id,historia)
    }

    @GetMapping("/{id}/historiaClinica")
    fun getHistoria(@PathVariable id: Long) = ResponseEntity.ok(pacienteService.getHistoria(id))

    @GetMapping("/{id}")
    fun getPaciente(@PathVariable id: Long) = ResponseEntity.ok(pacienteService.getPaciente(id))

    @GetMapping("/{id}/odontograma")
    fun getPiezas(@PathVariable id: Long) = ResponseEntity.ok(pacienteService.getPiezas(id))

    @PostMapping("/save")
    fun save(@RequestBody paciente: Paciente): ResponseEntity<Paciente> {
        return ResponseEntity.ok(pacienteService.save(paciente))
    }

    @GetMapping("/search")
    fun searchPacientes(@RequestParam("filtro") filtro: String) : ResponseEntity<List<Paciente>>{
        return ResponseEntity.ok(pacienteService.searchPacientes(filtro))
    }

    @DeleteMapping("/delete/{id}")
    fun deletePaciente(@PathVariable id:Long){
        ResponseEntity.ok(pacienteService.delete(id))
    }

    @PutMapping("/{id}/update")
    fun updatePaciente(@RequestBody paciente:Paciente): Paciente {

        return pacienteService.update(paciente)
    }

    @PostMapping("/{id}/file")
    @Throws(IOException::class)
    fun uploadFile(@PathVariable id: Long ,@RequestParam("file") file: MultipartFile?): ResponseEntity<Archivo> {

        var archivo = pacienteService.saveFile(id,file)
        return ResponseEntity.ok(archivo)

    }

    @PostMapping("/{id}/tratamiento")
    @Throws(IOException::class)
    fun addTratamiento(@PathVariable id: Long ,@RequestBody tratamiento: Tratamiento): ResponseEntity<Tratamiento> {

        var archivo = pacienteService.saveTratamiento(id,tratamiento)
        return ResponseEntity.ok(archivo)

    }

    @GetMapping("/{id}/{fileName}")
    fun downloadFile(@PathVariable id: Long ,@PathVariable fileName: String?): ResponseEntity<Resource>? {
        val archivo: Archivo? = pacienteService.getFile(id ,fileName)
        return ResponseEntity.status(HttpStatus.OK)
            .contentType(MediaType.parseMediaType(archivo!!.tipo))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + archivo!!.nombre + "\"")
            .body(archivo!!.data?.let { ByteArrayResource(it) })
    }

    @GetMapping("/{id}/files")
    fun getFiles(@PathVariable id: Long ): ResponseEntity<List<Archivo>> {
        return ResponseEntity.ok(pacienteService.getFiles(id))
    }

    @GetMapping("/{id}/tratamientos")
    fun getTratamientos(@PathVariable id: Long ): ResponseEntity<List<Tratamiento>> {
        return ResponseEntity.ok(pacienteService.getTratamientos(id))
    }


}