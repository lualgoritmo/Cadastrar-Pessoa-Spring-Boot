package com.luciano.cadastropessoa.cadastrarpessoa.exception

import UniqueValue
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.springframework.stereotype.Component
import kotlin.reflect.KClass
@Component
class UniqueValueValidator(
    @PersistenceContext private val manager: EntityManager
) : ConstraintValidator<UniqueValue, Any?> {

    private lateinit var domainAttribute: String
    private lateinit var klass: KClass<*>

    override fun initialize(param: UniqueValue) {
        domainAttribute = param.fieldName
        klass = param.domainClass
    }
    //@Transactional(value = Transactional.TxType.REQUIRED)
    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        val query = manager.createQuery("select 1 from ${klass.qualifiedName} where upper($domainAttribute) = :value")
        query.setParameter("value", value)
        val list = query.resultList
        return list.isEmpty()
    }
}
