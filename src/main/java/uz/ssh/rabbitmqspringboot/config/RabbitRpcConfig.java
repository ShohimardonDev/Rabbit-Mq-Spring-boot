package uz.ssh.rabbitmqspringboot.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitRpcConfig {

    private final String RPC_QUEUE;

    public RabbitRpcConfig(@Value("${rpc.queue}") String rpcQueue) {
        RPC_QUEUE = rpcQueue;
    }

    @Bean
    Queue rpcQueue() {
        return new Queue(RPC_QUEUE, true);
    }
}
