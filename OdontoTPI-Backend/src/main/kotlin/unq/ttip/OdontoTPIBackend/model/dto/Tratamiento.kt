package unq.ttip.OdontoTPIBackend.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.format.annotation.DateTimeFormat
import java.text.DateFormat
import java.time.LocalDateTime
import javax.persistence.*


@Entity
class Tratamiento {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(name="fecha")
    @JsonProperty("fecha")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    var fecha: LocalDateTime = LocalDateTime.now()

    @JsonProperty(value = "tratamiento")
    var tratamiento: String = ""

    @Column(columnDefinition="TEXT", length = 2048)
    @JsonProperty(value = "firma")
    var firma: String = ""

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_legajo", nullable = false)
    private var paciente: Paciente? = null

    constructor(fecha: LocalDateTime, tratamiento: String, firma: String, paciente: Paciente?) {
        this.fecha = fecha
        this.tratamiento = tratamiento
        this.firma = firma
        this.paciente = paciente
    }

    public fun setPaciente(paciente: Paciente){
        this.paciente= paciente
    }
}
