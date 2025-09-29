package uz.ssh.rabbitmqspringboot.service.topic;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import uz.ssh.rabbitmqspringboot.config.TopicConfig;

@Service
@RequiredArgsConstructor
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public void sendUserCreated(String message) {
        rabbitTemplate.convertAndSend(
                TopicConfig.EXCHANGE_NAME,
                "user.created",   // topic key
                message
        );
    }

    public void sendOrderUpdate(String message) {
        rabbitTemplate.convertAndSend(
                TopicConfig.EXCHANGE_NAME,
                "order.updated.us",
                message
        );
    }
}
