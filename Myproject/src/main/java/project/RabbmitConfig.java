package project;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbmitConfig {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange("fanout1");
    }

    @Bean
    public FanoutExchange fanoutExchange2() {
        return new FanoutExchange("fanout2");
    }

    @Bean
    public CustomExchange delayedExchange() {
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange("delayedExchange", "x-delayed-message", true, false, args);
    }

    @Bean
    public Queue delayedQueue() {
        return new Queue("delayedQueue");
    }



    @Bean
    public Binding delayedBinding(Queue delayedQueue, CustomExchange delayedExchange) {
        return BindingBuilder.bind(delayedQueue).to(delayedExchange).with("delayedRoutingKey").noargs();
    }

    @Bean
    public Queue fanoutQueue1() {
        return new Queue("queue1");
    }

    @Bean
    public Queue fanoutQueue2() {
        return new Queue("queue2");
    }

    @Bean
    public Binding bindingQueue1(Queue fanoutQueue1, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }


    @Bean
    public Binding bindingQueue2(Queue fanoutQueue2, FanoutExchange fanoutExchange2) {
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange2);
    }
}
