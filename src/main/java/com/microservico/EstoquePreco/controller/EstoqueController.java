package com.microservico.EstoquePreco.controller;

import com.microservico.EstoquePreco.constantes.RabbitMQConstantes;
import com.microservico.EstoquePreco.dto.EstoqueDto;
import com.microservico.EstoquePreco.services.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "estoque")
public class EstoqueController {

    @Autowired
    RabbitMQService _rabbitMQService;

    public EstoqueController(RabbitMQService rabbitMQService){
        this._rabbitMQService = rabbitMQService;
    }
    @PutMapping
    private ResponseEntity alteraEstoque (@RequestBody EstoqueDto estoqueDto){
        System.out.println(estoqueDto.codigoProtudo);
        if(estoqueDto != null){
            this._rabbitMQService.enviaMenssagem(RabbitMQConstantes.FILA_ESTOQUE, estoqueDto);
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }

}
