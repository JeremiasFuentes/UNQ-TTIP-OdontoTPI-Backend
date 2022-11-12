package unq.ttip.OdontoTPIBackend.model.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.ttip.OdontoTPIBackend.model.dto.HistoriaClinica
import unq.ttip.OdontoTPIBackend.model.dto.Paciente
import unq.ttip.OdontoTPIBackend.model.dto.PiezaDentaria
import unq.ttip.OdontoTPIBackend.model.dto.Turno
import unq.ttip.OdontoTPIBackend.model.service.PacienteService
import unq.ttip.OdontoTPIBackend.model.service.TurnoService


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




}