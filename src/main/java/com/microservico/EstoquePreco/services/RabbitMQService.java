package com.microservico.EstoquePreco.services;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    public RabbitMQService(){

    }
    public void enviaMenssagem(String nomeFile, Object mensagem){
        this.rabbitTemplate.convertAndSend(nomeFile,mensagem);
    }
}
