package ru.sotn.transactionservice.feign;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sotn.transactionservice.model.OrderDto;

@FeignClient("order-service")
public interface OrderTransactionClient {

    @PostMapping("/transaction")
    OrderDto getOrderInTransaction(@RequestParam("email") String email);
}
