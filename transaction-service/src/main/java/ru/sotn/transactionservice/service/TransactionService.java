package ru.sotn.transactionservice.service;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import ru.sotn.transactionservice.model.OrderDto;

public interface TransactionService {
//
//    void startTransaction(OrderDto orderDto);
    Charge charge(String email, String token) throws Exception;
}
