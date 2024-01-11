import com.luciano.cadastropessoa.cadastrarpessoa.exception.UniqueValueValidator
import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD)
@Retention(AnnotationRetention.RUNTIME)
@Constraint(validatedBy = [UniqueValueValidator::class])
annotation class UniqueValue(
    val message: String = "{br.com.zup.casadocodigo.validators}",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = [],
    val fieldName: String,
    val domainClass: KClass<*>
)
