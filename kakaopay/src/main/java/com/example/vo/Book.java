package com.example.vo;

import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class Book{
	private int no;
    private String title;
    private String author;
    private String publisher;
    private String description;
    private int price;
    private int discountPrice;
    private int stock;
    private Date updatedDate;
    private Date createdDate;

    public Book(String title, String author, String publisher, String description, int price, int stock) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.price = price;
        this.discountPrice = (int) Math.round(price*0.9);
        this.stock = stock;
    }
   
}
