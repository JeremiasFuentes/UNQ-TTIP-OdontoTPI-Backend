package unq.ttip.OdontoTPIBackend.model.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import unq.ttip.OdontoTPIBackend.model.dto.Paciente
import unq.ttip.OdontoTPIBackend.model.dto.Turno
import unq.ttip.OdontoTPIBackend.model.repository.PacienteRepository
import unq.ttip.OdontoTPIBackend.model.repository.TurnoRepository
import javax.transaction.Transactional


@Transactional
@Service
class TurnoService {

    @Autowired
    lateinit var turnoRepository: TurnoRepository

    @Autowired
    lateinit var pacienteRepository: PacienteRepository

    fun getAll() = turnoRepository.findAll()


}