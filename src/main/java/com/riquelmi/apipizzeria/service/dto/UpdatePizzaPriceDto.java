package com.riquelmi.apipizzeria.service.dto;

import lombok.Data;

@Data
public class UpdatePizzaPriceDto {
    private int pizzaId;
    private double newPrice;
}
