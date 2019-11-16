package com.shenbo.amqp.service;

import com.shenbo.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @RabbitListener(queues = {"shenbo.news"})
    public void receive(Book book){
        System.out.println("收到消息:"+book);
    }

    @RabbitListener(queues = {"shenbo"})
    public void receice02(Message message){

        System.out.println(message.getBody());
        System.out.println(message.getMessageProperties());

    }
}
