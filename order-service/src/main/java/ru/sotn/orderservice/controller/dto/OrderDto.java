package ru.sotn.orderservice.controller.dto;

import lombok.Data;
import ru.sotn.orderservice.domain.Order;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A DTO for the {@link Order} entity
 */
@Data
public final class OrderDto implements Serializable {

    private  String orderNumber;
    private  String email;
    private  String address;
    private  List<OrderLineItemDto> orderLineItemsList = new ArrayList<>();



}