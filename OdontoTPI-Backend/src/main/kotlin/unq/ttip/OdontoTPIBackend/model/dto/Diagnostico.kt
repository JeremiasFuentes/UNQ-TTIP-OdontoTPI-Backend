package unq.ttip.OdontoTPIBackend.model.dto

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Diagnostico {
    @Id
    @GeneratedValue
    var id: Long? = null
    private val value: String? = null

}
