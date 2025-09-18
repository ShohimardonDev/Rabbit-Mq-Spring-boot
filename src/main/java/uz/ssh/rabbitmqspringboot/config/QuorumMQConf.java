package uz.ssh.rabbitmqspringboot.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Shokhimardon
 * @since 9/17/25
 */
@Configuration
public class QuorumMQConf {

    @Bean("durableQuorumPriorityQueue")
    public Queue durableQuorumPriorityQueue() {
        return QueueBuilder.durable("orders.quorum")
                .withArgument("x-queue-type", "quorum")
                .withArgument("x-max-priority", 10)
                .build();
    }

    @Bean("singleActiveConsumerQueue")
    public Queue singleActiveConsumerQueue() {
        return QueueBuilder.durable("payments.sac")
                .withArgument("x-single-active-consumer", true)
                .build();
    }

    @Bean("priorityConsumerQueue")
    public Queue priorityConsumerQueue() {
        return QueueBuilder.durable("task.queue")
                .withArgument("x-consumer-priority", 5) // consumers can declare different priorities
                .build();
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            CachingConnectionFactory connectionFactory
    ) {
        SimpleRabbitListenerContainerFactory f = new SimpleRabbitListenerContainerFactory();
        f.setConnectionFactory(connectionFactory);
        f.setConcurrentConsumers(3);
        f.setMaxConcurrentConsumers(10);
        f.setPrefetchCount(20);
        f.setAcknowledgeMode(AcknowledgeMode.AUTO);
        return f;
    }

}
