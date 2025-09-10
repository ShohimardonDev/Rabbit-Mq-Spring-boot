package uz.ssh.rabbitmqspringboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfig {

    @Value("${routing.queue}")
    private String queueName;

    @Value("${routing.queue-2}")
    private String queueName2;

    @Value("${routing.exchange}")
    private String exchangeName;

    @Value("${routing.routing}")
    private String routingName;

    @Value("${routing.routing-2}")
    private String routingName2;

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeName);
    }

    @Bean("routing.queue")
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean("routing.queue-2")
    public Queue queue2() {
        return new Queue(queueName2, true);
    }

    @Bean("routing-binding-1")
    public Binding binding1(DirectExchange direct,
                            @Qualifier("routing.queue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(direct)
                .with(routingName);
    }

    @Bean("routing-binding-2")
    public Binding binding2(DirectExchange direct,
                            @Qualifier("routing.queue-2") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(direct)
                .with(routingName2);
    }
}
