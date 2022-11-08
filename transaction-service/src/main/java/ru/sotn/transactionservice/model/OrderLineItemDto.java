package ru.sotn.transactionservice.model;

import lombok.Data;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;


@Data
@Setter
public final class OrderLineItemDto implements Serializable {

    private  String skuCode;
    private  BigDecimal price;
    private  Integer quantity;

}