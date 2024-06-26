package project;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Delayed;

@Component
public class Send {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void testSimpleQueue() {
        String message = "哈啊啊";
        String exchange = "fanout1";
        // Sending message to the fanout exchange
        rabbitTemplate.convertAndSend(exchange, "", message);
        System.out.println("Message Sent: " + message);
    }

    public void testDelayMessage( String message, int delayInMilliseconds) {
//        String message2 = "cccc";
//        String exchange2 = "my";
//        rabbitTemplate.convertAndSend(exchange2, "", message2);
//        System.out.println("Message Sent: " + message2);
//

        rabbitTemplate.convertAndSend("delayedExchange", "delayedRoutingKey", message, msg -> {
            msg.getMessageProperties().setHeader("x-delay", delayInMilliseconds);
            return msg;
        });
    }
}
