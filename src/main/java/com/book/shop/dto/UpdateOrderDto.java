package com.book.shop.dto;

import com.book.shop.models.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateOrderDto {
    @NotNull(message = "Status cannot be null")
    private Status status;
}
