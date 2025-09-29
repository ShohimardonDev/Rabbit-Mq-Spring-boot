package uz.ssh.rabbitmqspringboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shokhimardon
 * @since 9/28/25
 */
@Configuration
public class RabbitMqFaunoutConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("logs_exchange");
    }

    @Bean
    public Queue emailQueue() {
        return new Queue("queue_email");
    }

    @Bean
    public Queue smsQueue() {
        return new Queue("queue_sms");
    }

    @Bean
    public Binding bindEmailQueue(FanoutExchange fanoutExchange, Queue emailQueue) {
        return BindingBuilder.bind(emailQueue).to(fanoutExchange);
    }

    @Bean
    public Binding bindSmsQueue(FanoutExchange fanoutExchange, Queue smsQueue) {
        return BindingBuilder.bind(smsQueue).to(fanoutExchange);
    }

}
