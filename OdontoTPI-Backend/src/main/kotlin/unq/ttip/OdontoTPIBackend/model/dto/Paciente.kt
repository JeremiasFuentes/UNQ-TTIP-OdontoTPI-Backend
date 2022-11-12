package unq.ttip.OdontoTPIBackend.model.dto

import javax.persistence.*


@Entity
class Paciente {
    @Id
    @GeneratedValue
    var Legajo: Long? = null

    @ElementCollection
    private var diags: MutableList<String>? = null
    var nombre: String = ""

    var apellido: String = ""
    var dni: String = ""
    var mail: String = ""
    var tel: String = ""
    var domicilio : String = ""
    var adulto: Boolean = true

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var piezas: MutableList<PiezaDentaria>? = mutableListOf()

    @OneToMany(mappedBy = "paciente", fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL]
    )
    var turnos: MutableList<Turno>? = mutableListOf()

    @OneToOne(cascade = [CascadeType.ALL] , fetch = FetchType.EAGER)
    @JoinColumn(name = "historiaClinica_id", referencedColumnName = "id")
    var historiaClinica: HistoriaClinica = HistoriaClinica()

    constructor(nombre: String, apellido: String, dni: String, mail: String, tel: String, domicilio:String, adulto: Boolean) {
        this.nombre = nombre
        this.apellido = apellido
        this.dni = dni
        this.mail = mail
        this.tel = tel
        this.domicilio = domicilio
        this.adulto = adulto
    }


    public fun getDiags(): MutableList<String>? { return diags}

    public fun setDiags(diagnosticos: MutableList<String>) {this.diags = diagnosticos}

    fun setPiezasDentaria(piezas: MutableList<PiezaDentaria>) {
        this.piezas = piezas
    }

    fun getPiezasDentarias(): MutableList<PiezaDentaria>? {
        return this.piezas
    }

    fun setHistoriaClinca(historia: HistoriaClinica) {
        this.historiaClinica = historia
    }

    fun getHistoria(): HistoriaClinica {
        return this.historiaClinica
    }

    fun setTurnosDePaciente(turnos: MutableList<Turno>) {
        this.turnos = turnos
    }

    fun getTurnosDePaciente(): MutableList<Turno>? {
        return this.turnos
    }

}