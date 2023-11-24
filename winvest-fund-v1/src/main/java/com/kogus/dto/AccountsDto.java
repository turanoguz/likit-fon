package com.kogus.dto;


import lombok.Data;

@Data
public class AccountsDto {


    private int customerId;

    private String accountName = "deneme hesabi";

    private double totalMoney;
}
