package unq.ttip.OdontoTPIBackend.model.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import unq.ttip.OdontoTPIBackend.model.dto.Diagnostico
import unq.ttip.OdontoTPIBackend.model.dto.Paciente
import unq.ttip.OdontoTPIBackend.model.repository.PacienteRepository
import unq.ttip.OdontoTPIBackend.model.service.PacienteService


@RestController
@CrossOrigin
@RequestMapping("paciente")
class PacienteController {

    @Autowired
    lateinit var pacienteService: PacienteService

    @GetMapping
    fun pacientes() = ResponseEntity.ok(pacienteService.getAll())

    @PutMapping("/{id}/diag")
    fun addDiagostico(@PathVariable id:Long, @RequestBody diagnostico:String): Paciente {
        var paciente = pacienteService.getPaciente(id).get()
        var diagnosticos = paciente.getDiags()
        diagnosticos!!.add(diagnostico)
        paciente.setDiags(diagnosticos)
        return pacienteService.save(paciente)
    }

    @GetMapping("/{id}")
    fun getPaciente(@PathVariable id: Long) = ResponseEntity.ok(pacienteService.getPaciente(id))

    @PostMapping("/save")
    fun save(@RequestBody paciente: Paciente){
        ResponseEntity.ok(pacienteService.save(paciente))
    }

    @GetMapping("/search")
    fun searchPacientes(@RequestParam("filtro") filtro: String) : ResponseEntity<List<Paciente>>{
        return ResponseEntity.ok(pacienteService.searchPacientes(filtro))
    }


}