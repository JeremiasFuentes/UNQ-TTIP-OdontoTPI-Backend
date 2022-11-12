package unq.ttip.OdontoTPIBackend.model.dto

import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.persistence.*

@Entity
class Turno {

    @Id
    @GeneratedValue
    var id: Long? = null

    @Column(name="Subject")
    @JsonProperty("Subject")
    var Subject: String = ""

    @Column(name="StartTime")
    @JsonProperty("StartTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    var StartTime: LocalDateTime = LocalDateTime.now()

    @Column(name="EndTime")
    @JsonProperty("EndTime")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonFormat(pattern = "YYYY-MM-dd HH:mm")
    var EndTime: LocalDateTime = LocalDateTime.now()

    @Column(name="Description")
    @JsonProperty("Description")
    var Description: String = ""

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_legajo", nullable = false)
    private var paciente: Paciente? = null

    public fun setPaciente(paciente: Paciente){
        this.paciente= paciente
    }
}