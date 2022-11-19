package unq.ttip.OdontoTPIBackend.model.model

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
class RespondeDataFile(nombre: String, downloadURL: String, tipo: String, size: Long) {

    val fileName: String =""
    val downloadURL: String = ""
    val type: String = ""
    val size: Long = 0


}