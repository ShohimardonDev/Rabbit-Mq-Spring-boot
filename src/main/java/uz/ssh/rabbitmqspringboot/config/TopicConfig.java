package uz.ssh.rabbitmqspringboot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shokhimardon
 * @since 9/23/25
 */
@Configuration
@RequiredArgsConstructor
public class TopicConfig {

    public static final String EXCHANGE_NAME = "topic_exchange";
    public static final String QUEUE_USER = "user_queue";
    public static final String QUEUE_ORDER = "order_queue";

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue userQueue() {
        return new Queue(QUEUE_USER, true);
    }

    @Bean
    public Queue orderQueue() {
        return new Queue(QUEUE_ORDER, true);
    }

    // Binding with routing patterns
    @Bean
    public Binding bindingUserQueue() {
        // receives messages with routing keys like: user.created, user.updated
        return BindingBuilder.bind(userQueue())
                .to(topicExchange())
                .with("user.*");
    }

    @Bean
    public Binding bindingOrderQueue() {
        // receives messages like: order.created, order.*
        return BindingBuilder.bind(orderQueue())
                .to(topicExchange())
                .with("order.#");
    }
}
