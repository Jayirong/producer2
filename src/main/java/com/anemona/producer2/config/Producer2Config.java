package com.anemona.producer2.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Producer2Config {
    public static final String QUEUE_HISTORICO = "historico.queue";
    public static final String EXCHANGE_HISTORICO = "historico.exchange";
    public static final String ROUTING_KEY = "historico.nuevo";

    @Bean
    public Queue alertQueue() {
        return new Queue(QUEUE_HISTORICO, true);
    }

    @Bean 
    public DirectExchange alertExchange() {
        return new DirectExchange(EXCHANGE_HISTORICO);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder
            .bind(alertQueue())
            .to(alertExchange())
            .with(ROUTING_KEY);
    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }
    
}
