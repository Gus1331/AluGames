import java.time.LocalDate
import java.time.Period

fun LocalDate.calcularIdade():Int{
    return Period.between(this, LocalDate.now()).years
}