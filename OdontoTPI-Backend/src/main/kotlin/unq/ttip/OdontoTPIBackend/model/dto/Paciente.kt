package unq.ttip.OdontoTPIBackend.model.dto

import org.hibernate.annotations.OnDelete
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

    constructor(nombre: String, apellido: String, dni: String, mail: String, tel: String) {
        this.nombre = nombre
        this.apellido = apellido
        this.dni = dni
        this.mail = mail
        this.tel = tel
    }


    public fun getDiags(): MutableList<String>? { return diags}

    public fun setDiags(diagnosticos: MutableList<String>) {this.diags = diagnosticos}


}