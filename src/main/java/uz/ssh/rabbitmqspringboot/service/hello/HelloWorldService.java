package uz.ssh.rabbitmqspringboot.service.hello;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

@Service
@RequiredArgsConstructor
@Slf4j
public class HelloWorldService {
    private final RabbitTemplate rabbitTemplate;
    @Value("${hello.world.queue}")
    private String queueName;

    public void sayHello(String name) {
        StopWatch watch = new StopWatch();
        watch.start("temp");
        rabbitTemplate.convertAndSend(queueName, name);
    }

    @RabbitListener(queues = "${hello.world.queue}")
    public void sayedHello(String name) {
        log.info("sayed hello {}", name);
    }
}
