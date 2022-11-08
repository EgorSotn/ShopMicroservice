package ru.sotn.transactionservice.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AbstractOAuth2Token;
import org.springframework.security.oauth2.server.resource.authentication.AbstractOAuth2TokenAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.sotn.transactionservice.config.AppProps;
import ru.sotn.transactionservice.config.OrderUtil;
import ru.sotn.transactionservice.feign.OrderTransactionClient;
import ru.sotn.transactionservice.model.OrderDto;
import ru.sotn.transactionservice.model.PaymentDto;
import ru.sotn.transactionservice.stripe.StripeClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ru.sotn.transactionservice.config.ConstKafka.NAME_TOPIC_CONSUMER_NOTIFICATION;
import static ru.sotn.transactionservice.config.ConstKafka.NAME_TOPIC_CONSUMER_TRANSACTION;


@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
    private final KafkaTemplate<Long, PaymentDto> kafkaTemplate;
//    private List<OrderDto> orderDtos = new ArrayList<>();
    private final OrderTransactionClient orderTransactionClient;
    private final StripeClient stripeClient;

//    @Override
//    @KafkaListener(topics = NAME_TOPIC_CONSUMER_TRANSACTION)
//    public void startTransaction(OrderDto orderDto) {
//        log.info("=> consumer {}", orderDto.getEmail());
//
////        orderDtos.add(orderDto);
//
//        kafkaTemplate.send(NAME_TOPIC_CONSUMER_NOTIFICATION, orderDto);
//    }

    @Override
    public Charge charge(String email,String token) throws Exception {
        OrderDto orderDto = orderTransactionClient.getOrderInTransaction(email);
        Long amount = OrderUtil.calculateOrderAmountInCents(orderDto);
        Charge charge = new Charge();
        if(token != null){
             charge = stripeClient.chargeNewCard(token,amount);
             log.info("token: {} accept with front", token);
        }
        PaymentDto paymentDto = new PaymentDto(orderDto.getOrderNumber(),orderDto.getEmail(),
                orderDto.getAddress(),orderDto.getOrderLineItemsList(),amount);

        kafkaTemplate.send(NAME_TOPIC_CONSUMER_NOTIFICATION, paymentDto);
        log.info("kafa send massage: {}", orderDto.getEmail());
        return charge;
    }

}
