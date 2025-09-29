package uz.ssh.rabbitmqspringboot.service.fanout;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author Shokhimardon
 * @since 9/28/25
 */
@Service
@RequiredArgsConstructor
public class FanoutService {
    private final RabbitTemplate rabbitTemplate;

    public void sendLog(String message) {
        rabbitTemplate.convertAndSend("logs_exchange", "", message);
    }
}
