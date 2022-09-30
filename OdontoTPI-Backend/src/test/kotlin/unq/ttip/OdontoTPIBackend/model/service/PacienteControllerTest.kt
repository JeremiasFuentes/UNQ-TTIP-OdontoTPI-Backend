package unq.ttip.OdontoTPIBackend.model.service

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import unq.ttip.OdontoTPIBackend.model.controller.PacienteController
import unq.ttip.OdontoTPIBackend.model.dto.Paciente


internal class PacienteControllerTest {

    @Mock
    lateinit var pacienteService: PacienteService

    @InjectMocks
    lateinit var pacienteController: PacienteController

    lateinit var paciente : Paciente


    @BeforeEach
    fun setUp(){
        MockitoAnnotations.openMocks(this)
        paciente = Paciente("jere","fuentes","12345678","jfuentes@gmail.com","1232456677")

    }

    @Test
    fun pacientes() {
        var pacientes: MutableList<Paciente>? = mutableListOf()
        pacientes!!.add(paciente)
        Mockito
            .`when`(pacienteService.getAll())
            .thenReturn(pacientes)
        val response: ResponseEntity<*> = pacienteController.pacientes()
        assertEquals(response.body,pacientes)
        assertEquals(response.statusCode, HttpStatus.OK)
    }

    @Test
    fun save() {
        Mockito
            .`when`(pacienteService.save(paciente))
            .thenReturn(paciente)
        val response: ResponseEntity<Paciente> = pacienteController.save(paciente)
        assertEquals(response.body,paciente)
    }


    @Test
    fun updatePaciente() {
        var paciente2 = Paciente("jeremias","fuentes","12345678","jfuentes@gmail.com","1232456677")
        Mockito
            .`when`(pacienteService.save(paciente))
            .thenReturn(paciente2)
        val response: Paciente = pacienteController.updatePaciente(paciente)
        assertEquals(response,paciente2)

    }

}