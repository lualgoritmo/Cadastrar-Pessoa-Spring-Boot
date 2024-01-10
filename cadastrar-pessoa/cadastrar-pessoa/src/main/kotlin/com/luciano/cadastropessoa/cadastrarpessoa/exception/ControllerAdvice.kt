package com.luciano.cadastropessoa.cadastrarpessoa.exception

import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.WebRequest

@RestControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(AuthorNotFoundException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleExceptionIdAuthor(ex: AuthorNotFoundException, request: WebRequest): ErrorResponse {
        return ErrorResponse(
            400,
            "id incorreto, não encontrado no servidor!",
            "0001",
            null
        )
    }
    @ExceptionHandler(CategoryNotFoundException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleExceptionIdCategory(ex: CategoryNotFoundException, request: WebRequest): ErrorResponse {
        return ErrorResponse(
            400,
            "id da categoria incorreto, não encontrado no servidor!",
            "0002",
            null
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(ex: Exception): Map<String, String> {
        val errorMap = HashMap<String, String>()
        errorMap["error"] = ex.message ?: "Internal Server Error"
        return errorMap
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleException(ex: MethodArgumentNotValidException): Map<String, Any> {
        val errorMap = HashMap<String, Any>()
        errorMap["error"] = "Caiu na validação, nome null ou vazio"

        val errors = HashMap<String, String>()

        val bindingResult: BindingResult = ex.bindingResult
        for (fieldError: FieldError in bindingResult.fieldErrors) {
            errors[fieldError.field] = fieldError.defaultMessage ?: "Unknown error"
        }

        errorMap["errors"] = errors
        return errorMap
    }
    @ExceptionHandler(HttpMessageNotReadableException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleJsonParseException(ex: HttpMessageNotReadableException): Map<String, String> {
        val errorMap = HashMap<String, String>()

        if (ex.cause is com.fasterxml.jackson.databind.JsonMappingException) {
            val fieldName = (ex.cause as com.fasterxml.jackson.databind.JsonMappingException).path?.map { it.fieldName }?.joinToString(" -> ")

            if (fieldName != null) {
                errorMap["error"] = "Erro na desserialização JSON: O campo '$fieldName' não pode ser nulo."
            } else {
                errorMap["error"] = "Erro na desserialização JSON: ${ex.localizedMessage}"
            }
        } else {
            errorMap["error"] = "Erro na desserialização JSON: ${ex.localizedMessage}"
        }

        return errorMap
    }

}
