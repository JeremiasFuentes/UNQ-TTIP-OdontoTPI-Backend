package unq.ttip.OdontoTPIBackend.model.dto

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import javax.persistence.*


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
class Archivo {

    @Id
    @GeneratedValue
    var id: Long? = null

    var nombre: String = ""
    var tipo: String = ""
    var downloadURL: String = ""
    var data: ByteArray? = null

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "paciente_legajo", nullable = false)
    private var paciente: Paciente? = null

    constructor(nombre: String, tipo: String, downloadURL: String, data: ByteArray?, paciente: Paciente) {
        this.nombre = nombre
        this.tipo = tipo
        this.downloadURL = downloadURL
        this.data = data
        this.paciente = paciente
    }

    public fun setPaciente(paciente: Paciente){
        this.paciente= paciente
    }

    fun getDataArchivo() : ByteArray? {
        return this.data
    }


}