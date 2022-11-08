package ru.sotn.transactionservice.model;

import lombok.Data;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Data
public final class OrderDto implements Serializable {

    private  String orderNumber;
    private  String email;
    private  String address;
    private  List<OrderLineItemDto> orderLineItemsList = new ArrayList<>();



}