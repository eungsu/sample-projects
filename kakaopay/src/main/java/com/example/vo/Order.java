package com.example.vo;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
    private String id;
    private int userNo;
    private String orderStatus;
    private int totalPrice;
    private Date updatedDate;
    private Date createdDate;
}
