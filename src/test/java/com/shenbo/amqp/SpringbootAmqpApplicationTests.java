package com.shenbo.amqp;

import com.shenbo.amqp.bean.Book;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class SpringbootAmqpApplicationTests {


    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    AmqpAdmin amqpAdmin;


    @Test
    public void createExchange(){
//        amqpAdmin.declareExchange(new DirectExchange("yusiyi.exchanges"));
//        System.out.println("创建完成");

//        amqpAdmin.declareQueue(new Queue("yusiyi.queue",true));
//        System.out.println("创建队列完成");

        amqpAdmin.declareBinding(new Binding("yusiyi.queue",Binding.DestinationType.QUEUE
        ,"yusiyi.exchanges","amqp.siyi",null));

    }



    /**
     * 单播模式下的点对点模式
     */


    @Test
    void contextLoads() {

        //rabbitTemplate.send(exchange,routingkey,message);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第一个消息");
        map.put("data", Arrays.asList("sad","happy","ture"));
        rabbitTemplate.convertAndSend("exchange.direct","shenbo.news",map);
    }


    //将数据转化成json传递

    @Test
    public void receive(){
        Object o = rabbitTemplate.receiveAndConvert("shenbo.news");
        System.out.println(o.getClass());
        System.out.println(o);
    }

    @Test
    public void sendMsgBoardCast(){
        Map<String,Object> map = new HashMap<>();
        map.put("msg","这是第二个消息");
        map.put("data", Arrays.asList("sad","happy","ture"));
        rabbitTemplate.convertAndSend("exchange.fanout","",new Book("沈博是天才"));
        //rabbitTemplate.convertAndSend("exchange.fanout",map);
    }




}
