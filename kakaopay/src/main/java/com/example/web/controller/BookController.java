package com.example.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.BookService;

@Controller
@RequestMapping(path = "/book")
public class BookController {

	@Autowired
	private BookService bookSerivce;
	
	@GetMapping(path = "/list")
	public String list(Model model) {
		model.addAttribute("books", bookSerivce.getBooks());
		
		return "book/list";
	}
}
