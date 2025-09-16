package uz.ssh.rabbitmqspringboot.service.rpc;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RpcServer {
    private final Faker faker;

    @RabbitListener(queues = "${rpc.queue}")
    public String handleRequest(String request) {
        System.out.println(" [x] Received request: " + request);
        // simulate processing
        return "Response for: " + request.toUpperCase() + "\t\t\t" + faker.name().fullName();
    }
}
