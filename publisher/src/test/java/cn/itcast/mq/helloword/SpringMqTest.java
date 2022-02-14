package cn.itcast.mq.helloword;

import org.junit.jupiter.api.Test;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class SpringMqTest {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSimpleQueue(){
        //队列名称
        String queueName="simple.queue";
        //消息
        String message="hello,spring mq";
        //发送消息
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    public void testWorkQueue(){
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend("simple.queue","hello,spring mq"+i);
        }
    }

    @Test
    public void testFanoutExchange(){
        rabbitTemplate.convertAndSend("itcast.fanout","","hello,everyone!");
    }

    @Test
    public void testSendDirectExchange(){
        rabbitTemplate.convertAndSend("itcast.direct","blue","hello rabbitmq");
    }

    @Test
    public void testSendTopicExchange(){
        rabbitTemplate.convertAndSend("itcast.topic","china.news","孙悟空大战哥斯拉");
    }

    @Test
    public void testSendMp(){
        Map<String, Object> msg = new HashMap<>();
        msg.put("name","Jack");
        msg.put("age",18);
        rabbitTemplate.convertAndSend("object.queue",msg);

    }
}
