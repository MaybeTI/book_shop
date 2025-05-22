package com.book.shop.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateOrderDto {
    @NotBlank(message = "Shipping address cannot be blank")
    private String shippingAddress;
}
