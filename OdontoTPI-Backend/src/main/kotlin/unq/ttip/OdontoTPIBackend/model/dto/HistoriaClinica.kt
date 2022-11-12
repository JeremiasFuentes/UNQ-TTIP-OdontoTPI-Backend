package unq.ttip.OdontoTPIBackend.model.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToOne

@Entity
class HistoriaClinica {
    @Id
    @GeneratedValue
    var id: Long? = null

    var ultimoTratamiento: String = ""
    var enTratamientoMedico: Boolean = false
    var causaTratamiento: String = ""
    var medicamentos: Boolean = false
    var cualesMedicamentos: String = ""
    var hemorragias: Boolean = false
    var fueOperado: Boolean = false
    var causaOperaciones: String = ""
    var recibioTransfusion: Boolean = false
    var alergico: Boolean = false
    var alergias: String = ""
    var trastornosPsi: Boolean = false
    var diabetes : Boolean = false
    var problemasCardiacos : Boolean = false
    var hemofilia: Boolean = false
    var problemasNeurologicos: Boolean = false
    var problemasRenales: Boolean = false
    var dialisis: Boolean = false
    var hepatitis: Boolean = false
    var tuberculosis: Boolean = false
    var neumonia: Boolean = false
    var sifilis: Boolean = false
    var hipertension: Boolean = false
    var hiv: Boolean= false
    var embarazada: Boolean = false
    var infeccionsARepeticion: Boolean = false
    var presionArterialMax: Long = 0
    var presionArterialMin: Long = 0
    var pulso: Long = 0
    var frecuenciaRespiratoria: Long = 0

    @OneToOne(mappedBy = "historiaClinica")
    private val paciente: Paciente? = null



}