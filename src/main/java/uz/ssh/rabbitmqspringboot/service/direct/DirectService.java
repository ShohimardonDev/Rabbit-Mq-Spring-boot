package uz.ssh.rabbitmqspringboot.service.direct;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import uz.ssh.rabbitmqspringboot.constant.DirectSend;

@Service
@Slf4j
@RequiredArgsConstructor
public class DirectService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${routing.exchange}")
    private String exchange;

    @Value("${routing.routing}")
    private String routingKey1;

    @Value("${routing.routing-2}")
    private String routingKey2;

    public void send(String message, DirectSend send) {
        if (send.equals(DirectSend.QUEUE_1)) {
            rabbitTemplate.convertAndSend(exchange, routingKey1, message);
        }
        if (send.equals(DirectSend.QUEUE_2)) {
            rabbitTemplate.convertAndSend(exchange, routingKey2, message);
        }
    }

    @RabbitListener(queues = "${routing.queue}")
    public void handleQueue1(String message) {
        log.info("Received from Queue1:{} ", message);
    }

    @RabbitListener(queues = "${routing.queue-2}")
    public void handleQueue2(String message) {
        log.info("Received from Queue2:{} ", message);
    }
}
