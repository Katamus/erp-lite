package com.criscahub.erp_lite.persistence.rabbit.adapters;

import com.criscahub.erp_lite.domain.common.DomainEvent;
import com.criscahub.erp_lite.domain.entities.product.events.ProductCreated;
import com.criscahub.erp_lite.domain.exception.MyBussinessException;
import com.criscahub.erp_lite.domain.ports.messages.EventPublisherPort;
import com.criscahub.erp_lite.persistence.rabbit.config.RabbitMQConfig;
import com.criscahub.erp_lite.persistence.rabbit.dtos.ProductCreatedMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class RabbitMQEventPublisherAdapter  implements EventPublisherPort {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void publish(DomainEvent event) {
        if(event instanceof ProductCreated){
            final var msg = this.toMsg((ProductCreated) event);
            this.rabbitTemplate.convertAndSend(
                    RabbitMQConfig.EXCHANGE,
                    RabbitMQConfig.ROUTING_KEY,
                    msg
            );
            log.info("Published event: {} in exchage: {}", event, RabbitMQConfig.EXCHANGE);
        }else {
            log.warn("Published event {} not supported", event);
            throw new MyBussinessException("Event is not supported");
        }
    }

    private ProductCreatedMessage toMsg(ProductCreated event){
        return new ProductCreatedMessage(
                event.productId().value().toString(),
                event.sku().value(),
                event.name().value(),
                event.price().amount(),
                event.price().currency().getCurrencyCode(),
                event.timestamp(),
                event.description(),
                event.stock().value(),
                event.category().categoryId(),
                event.image() != null ? event.image().imageUrl() : null,
                event.active()

        );
    }

}
