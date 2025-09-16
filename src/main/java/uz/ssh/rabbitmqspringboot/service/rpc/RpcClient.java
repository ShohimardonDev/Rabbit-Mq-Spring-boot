package uz.ssh.rabbitmqspringboot.service.rpc;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RpcClient {

    private final RabbitTemplate rabbitTemplate;
    @Value("${rpc.queue}")
    public String queue;

    public String call(String message) {
        System.out.println(" [x] Sending RPC request: " + message);
        Object response = rabbitTemplate.convertSendAndReceive(
                "",
                queue,
                message
        );
        return response != null ? response.toString() : "No response!";
    }
}
