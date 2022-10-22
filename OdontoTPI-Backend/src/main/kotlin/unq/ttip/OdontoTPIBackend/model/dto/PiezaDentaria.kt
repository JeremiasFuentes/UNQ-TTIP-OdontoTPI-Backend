package unq.ttip.OdontoTPIBackend.model.dto

import javax.persistence.*


@Entity
class PiezaDentaria {

    @Id
    @GeneratedValue
    var id: Long? = null

    var numero: Int = 0
    var tipo: String = "normal"
    var abajo: String = "white"
    var arriba: String = "white"
    var centro: String = "white"
    var izquierda: String = "white"
    var derecha: String = "white"

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_legajo", nullable = false)
    private var paciente: Paciente? = null


    constructor(numero: Int) {
        this.numero = numero
    }

    public fun setPaciente(paciente: Paciente){
        this.paciente= paciente
    }
}