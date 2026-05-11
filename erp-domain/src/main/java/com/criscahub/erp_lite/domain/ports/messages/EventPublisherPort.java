package com.criscahub.erp_lite.domain.ports.messages;

import com.criscahub.erp_lite.domain.common.DomainEvent;

public interface EventPublisherPort {

    void publish(DomainEvent domainEvent);

}
