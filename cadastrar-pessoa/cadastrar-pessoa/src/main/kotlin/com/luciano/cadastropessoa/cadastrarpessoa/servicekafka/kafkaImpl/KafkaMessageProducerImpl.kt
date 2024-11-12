package com.luciano.cadastropessoa.cadastrarpessoa.servicekafka.kafkaImpl

import com.luciano.cadastropessoa.cadastrarpessoa.servicekafka.producer.KafkaMessageProducer
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture

//@Service
//class KafkaMessageProducerImpl(private val kafkaTemplate: KafkaTemplate<String, String>) {
//    fun sendMessage(topic: String, message: String) {
//        kafkaTemplate.send(topic, message)
//    }
//}
@Service
class KafkaMessageProducerImpl(private val kafkaTemplate: KafkaTemplate<String, String>): KafkaMessageProducer {

    override fun sendMessage(topic: String, key: String, message: String): String {
        val future: CompletableFuture<SendResult<String, String>> = kafkaTemplate.send(topic, key, message)

        future.thenAccept { result ->
            println(" KafkaMessageProducerImpl Mensagem enviada com sucesso: ${result.recordMetadata}")
        }

        future.exceptionally { ex ->
            println(" Erro ao enviar mensagem: ${ex.message}")
            null
        }

        return " Esse é o KafkaMessageProducerImpl, Mensagem enviada para o tópico $topic"
    }
}
