package com.openjudge.backend.Config;



import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



/**
 * Created by zy on 2020/2/11
 */

@Configuration
public class RabbitMQConfig {

    public final static String EXCHANGE_NAME ="openjudge";
    public final static String JUDGE_ITEM_QUEUE_NAME="judgeItem";
    public final static String JUDGE_ITEM_ROUTING_KEY="judgeItem";
    public final static String JUDGE_STATUS_QUEUE_NAME="judgeStatus";
    public final static String JUDGE_STATUS_ROUTING_KEY="judgeStatus";

    // 定义交换机
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    // 定义发送至评测机信息队列
    @Bean
    public Queue judgeItemQueue() {
        return new Queue(JUDGE_ITEM_QUEUE_NAME);
    }

    // 绑定路由
    @Bean
    public Binding judgeItemBinding(Queue judgeItemQueue, TopicExchange exchange) {
        return BindingBuilder.bind(judgeItemQueue).to(exchange).with(JUDGE_ITEM_ROUTING_KEY);
    }

    // 定义评测机发送状态信息队列
    @Bean
    public Queue judgeStatusQueue() {
        return new Queue(JUDGE_STATUS_QUEUE_NAME);
    }

    // 绑定路由
    @Bean
    public Binding judgeStatusBinding(Queue judgeStatusQueue, TopicExchange exchange) {
        return BindingBuilder.bind(judgeStatusQueue).to(exchange).with(JUDGE_STATUS_ROUTING_KEY);
    }

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private Integer port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;
    @Value("${spring.rabbitmq.virtual-host}")
    private String virtualHost;

    @Bean
    public CachingConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        return new RabbitTemplate(connectionFactory);
    }
}
