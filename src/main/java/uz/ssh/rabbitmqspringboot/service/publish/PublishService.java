package uz.ssh.rabbitmqspringboot.service.publish;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PublishService {
    private final RabbitTemplate rabbitTemplate;
    @Value("${publish.exchange}")
    private String exchangeName;


    public void publish(String message) {
        log.info("Publishing message: {}", message);
        rabbitTemplate.convertAndSend(exchangeName, "", message);
        System.out.println("Sent: " + message);
    }

    @RabbitListener(queues = "${publish.queue}")
    public void receiveFromQueue1(String message) {
        log.info("Received from queue: {}", message);
    }

    @RabbitListener(queues = "${publish.queue-2}")
    public void receiveFromQueue2(String message) {
        log.info("Received from queue-2: {}", message);
    }

}
