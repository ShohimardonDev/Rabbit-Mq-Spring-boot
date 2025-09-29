package uz.ssh.rabbitmqspringboot.service.topic;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import uz.ssh.rabbitmqspringboot.config.TopicConfig;

@Service
public class MessageConsumer {

    @RabbitListener(queues = TopicConfig.QUEUE_USER)
    public void receiveUserMessages(String message) {
        System.out.println("📩 User Queue: " + message);
    }

    @RabbitListener(queues = TopicConfig.QUEUE_ORDER)
    public void receiveOrderMessages(String message) {
        System.out.println("📩 Order Queue: " + message);
    }
}
