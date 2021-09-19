package com.microservico.EstoquePreco.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQService {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;
    public RabbitMQService(){

    }
    public void enviaMenssagem(String nomeFile, Object mensagem){
        try{
            String mensagemJson = this.objectMapper.writeValueAsString(mensagem);
            this.rabbitTemplate.convertAndSend(nomeFile,mensagemJson);
        }catch (Exception e){
             e.printStackTrace();
        }

    }
}
