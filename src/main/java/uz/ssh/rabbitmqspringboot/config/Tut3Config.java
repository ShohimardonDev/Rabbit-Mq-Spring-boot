package uz.ssh.rabbitmqspringboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Tut3Config {
    @Value("${publish.queue}")
    private String queueName;
    @Value("${publish.queue-2}")
    private String queueName2;
    @Value("${publish.exchange}")
    private String exchangeName;


    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange(exchangeName);
    }

    @Bean("publish.queue")
    public Queue queue() {
        return new Queue(queueName, true);
    }

    @Bean("publish.queue-2")
    public Queue queue2() {
        return new Queue(queueName2, true);
    }

    @Bean
    public Binding binding(FanoutExchange fanout,
                           @Qualifier("publish.queue") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(fanout);
    }

    @Bean
    public Binding binding2(FanoutExchange fanout,
                            @Qualifier("publish.queue-2") Queue queue) {
        return BindingBuilder
                .bind(queue)
                .to(fanout);
    }

}