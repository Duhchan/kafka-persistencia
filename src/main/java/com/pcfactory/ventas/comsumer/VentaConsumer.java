package com.pcfactory.ventas.comsumer;

import com.pcfactory.ventas.model.Venta;
import com.pcfactory.ventas.repository.VentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service

public class VentaConsumer {

    @Autowired
    private VentasRepository ventasRepository;

    @KafkaListener (topics = "${app.kafka.topic.ventas=ventas}")
    @KafkaListener(topics = "tutorial-particiones", groupId = "mi-grupo-educativo")
    public void listen(
            @Payload Venta venta, //aqui debemos llamar a nuestro payload que en esta ocasion seria nuestra clase venta
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset,
            @Header(value = KafkaHeaders.RECEIVED_KEY, required = false) String key) {

        //si aplicamos regla de negocio de comisiones (logica de negocio)
        Double comision = venta.getValor() * 0.1;
        Double valorConIva = venta.getValor() * 1.19;

        venta.setValor(valorConIva.longValue());

        ventasRepository.save(venta);
    }

}
