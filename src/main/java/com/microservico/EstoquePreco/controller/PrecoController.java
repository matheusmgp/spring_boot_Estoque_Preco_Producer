package com.microservico.EstoquePreco.controller;

import com.microservico.EstoquePreco.constantes.RabbitMQConstantes;
import com.microservico.EstoquePreco.dto.PrecoDto;
import com.microservico.EstoquePreco.services.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "preco")
public class PrecoController {

    @Autowired
    RabbitMQService _rabbitMQService;

    public PrecoController(RabbitMQService rabbitMQService){
        this._rabbitMQService = rabbitMQService;
    }

    @PutMapping
    private ResponseEntity alteraPreco (@RequestBody PrecoDto precoDto){
        if(precoDto != null){
            this._rabbitMQService.enviaMenssagem(RabbitMQConstantes.FILA_PRECO, precoDto);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

}
