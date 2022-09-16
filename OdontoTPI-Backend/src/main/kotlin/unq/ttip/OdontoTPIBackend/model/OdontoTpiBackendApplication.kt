package unq.ttip.OdontoTPIBackend.model

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication
class OdontoTpiBackendApplication

fun main(args: Array<String>) {
	runApplication<OdontoTpiBackendApplication>(*args)
}
