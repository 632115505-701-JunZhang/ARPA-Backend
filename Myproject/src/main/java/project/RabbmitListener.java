package project;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbmitListener {

    @RabbitListener(queues = "queue1")
    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
        System.out.println("消费者1接收到消息 ：【" + msg + "】");
    }

    @RabbitListener(queues = "delayedQueue")
    public void listenDelayQueue2Message(String msg) throws InterruptedException {
        System.out.println("消费者2接收到消息 ：【" + msg + "】");
    }
}
