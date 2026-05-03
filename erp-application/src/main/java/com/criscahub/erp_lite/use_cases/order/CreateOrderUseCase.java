package com.criscahub.erp_lite.use_cases.order;

import com.criscahub.erp_lite.commands.order.CreateOrderCommand;
import com.criscahub.erp_lite.domain.entities.order.Customer;
import com.criscahub.erp_lite.domain.entities.order.OrderItem;
import com.criscahub.erp_lite.domain.entities.order.OrderNumber;
import com.criscahub.erp_lite.domain.entities.order.OrderRoot;
import com.criscahub.erp_lite.domain.entities.product.ProductId;
import com.criscahub.erp_lite.domain.entities.product.ProductRoot;
import com.criscahub.erp_lite.domain.ports.repositories.OrderRepositoryPort;
import com.criscahub.erp_lite.domain.ports.repositories.ProductRepositoryPort;
import com.criscahub.erp_lite.domain.ports.services.CustomerProviderServicePort;
import com.criscahub.erp_lite.domain.ports.services.OrderConfirmEmailServicePorts;
import com.criscahub.erp_lite.domain.shared.CustomerId;
import com.criscahub.erp_lite.domain.shared.Email;
import com.criscahub.erp_lite.domain.shared.Quantity;
import com.criscahub.erp_lite.exceptions.CommandException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CreateOrderUseCase {

    private final OrderRepositoryPort orderRepository;
    private final ProductRepositoryPort productRepository;
    private final CustomerProviderServicePort customerProviderService;
    private final OrderConfirmEmailServicePorts orderConfirmEmailServicePorts;

    public String execute(CreateOrderCommand command){
        log.info("Create order {}",command);
        try {
            Customer customer = this.validateAndGet(command.customerId());
            List<OrderItem> items = this.createOrderItems(command.items());
            OrderNumber orderNumber = this.generateOrderNumber();
            OrderRoot orderRoot = OrderRoot.create(orderNumber,customer,items,command.createdBy());

            OrderRoot savedOrder = this.orderRepository.save(orderRoot);
            log.info("Saved order with is {}",savedOrder.getId());
//            sendEmail(orderRoot,customer);
            return orderRoot.getId().toString();
        }catch (IllegalArgumentException iae){
            log.error("Invalid data",iae);
            throw new CommandException("Error on create order message:"+iae.getMessage());
        }catch (Exception e){
            log.error("Unexpected error ",e);
            throw new CommandException(e.getMessage());
        }
    }

    private void sendEmail(OrderRoot root,Customer customer) {
        try {
            log.info("Sending mail:{}", customer.customerName()+"@gmail.com");

            orderConfirmEmailServicePorts.sendMail(
                    Email.of("criscahu@hotmail.com"),
                    root.getId(),
                    root.getOrderNumber().value(),
                    root.getTotalAmount(),
                    root.getCustomer().customerName(),
                    root.getItems().size()
            );

        } catch (Exception e) {
            log.error("Error sending mail",e);
            throw new CommandException("Error sending mail: "+e.getMessage());
        }


    }

    private Customer validateAndGet(Long customerId){
        log.info("Validating customer id {}",customerId);
       var customerInfo = this.customerProviderService.findById(customerId)
               .orElseThrow(()-> new CommandException("Customer not found is "+customerId));

       log.info("Customer validated id {}",customerInfo.name());

       return Customer.of(CustomerId.of(customerId),customerInfo.name());
    }

    private  List<OrderItem> createOrderItems(List<CreateOrderCommand.OrderItemRequest> commandItems){
        log.info("Creating order items");
        return commandItems.stream().map(this::toOrderItem).toList();
    }


    private OrderItem toOrderItem(CreateOrderCommand.OrderItemRequest commandItem){
        ProductRoot productRoot = this.productRepository
                .findById(ProductId.of(UUID.fromString(commandItem.productId()))).orElseThrow(()-> new CommandException("Product not found"));

        Quantity quantity = Quantity.of(commandItem.quantity());

        return  OrderItem.from(productRoot,quantity);
    }

    private OrderNumber generateOrderNumber(){
        int sequence = (int)(System.currentTimeMillis()%1000);
        return  OrderNumber.generate(sequence);
    }

    private void publishDomanEven(OrderRoot order){
        var events = order.getDomainEvents();
        log.debug("Publishing doman events: {}", events);
        events.forEach(event-> {
            log.debug("Try to publish event: {}",event);

        });
        order.clearDomainEvents();
        log.info("Events published successfully");
    }

}
