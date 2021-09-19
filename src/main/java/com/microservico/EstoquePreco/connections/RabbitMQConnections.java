package com.microservico.EstoquePreco.connections;


import constantes.RabbitMQConstantes;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class RabbitMQConnections {
    private static final String NOME_EXCHANGE = "amq.direct";
    private AmqpAdmin amqpAdmin;

    public RabbitMQConnections(AmqpAdmin amrpAdmin){
        this.amqpAdmin = amrpAdmin;

    }
    private Queue fila(String nomeFila){
        return  new Queue(nomeFila, true,false, false);
    }
    private DirectExchange exchange(){
        return new DirectExchange(NOME_EXCHANGE);
    }
    private Binding bind(Queue fila, DirectExchange exchange){
        return new Binding(fila.getName(),Binding.DestinationType.QUEUE, exchange.getName(),fila.getName(),null);
    }

    @PostConstruct
    private void adicionar(){

        //criando as filas
        Queue filaEstoque = this.fila(RabbitMQConstantes.FILA_ESTOQUE);
        Queue filaPreco = this.fila(RabbitMQConstantes.FILA_PRECO);

        //criando o exchange
        DirectExchange exchange = this.exchange();

        //dando o binding entre fila e exchange
        Binding bindingEstoque = this.bind(filaEstoque,exchange);
        Binding bindingPreco = this.bind(filaPreco,exchange);

        //declarando as filas no rabbitMQ
        this.amqpAdmin.declareQueue(filaEstoque);
        this.amqpAdmin.declareQueue(filaPreco);

        //declarando a exchange
        this.amqpAdmin.declareExchange(exchange);

        //criando as ligações entre fila e exchange
        this.amqpAdmin.declareBinding(bindingEstoque);
        this.amqpAdmin.declareBinding(bindingPreco);

    }


}
