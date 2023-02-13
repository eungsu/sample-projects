package com.example.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderItem{
   
    private int no;
    private int bookNo;
    private int quantity;
    private int price;
    private String orderId;
}
