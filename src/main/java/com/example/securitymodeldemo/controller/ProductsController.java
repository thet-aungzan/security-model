package com.example.securitymodeldemo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.securitymodeldemo.dao.ProductDao;
import com.example.securitymodeldemo.ds.Products;

@Controller
public class ProductsController {

	@Autowired
	private ProductDao productDao;
	
	@GetMapping("/products")
	public ModelAndView products() {
		return new ModelAndView("products","products",productDao.findAll());
	}
	
	@GetMapping("/products/create")
	public ModelAndView create() {
		return new ModelAndView("product-form","product",new Products());
	}
	
	@PostMapping("/products/create")
	public String create(@ModelAttribute @Valid Products products,BindingResult bs ) {
		if(bs.hasErrors()) {
			return "/products/create";
		}else {
			productDao.save(products);
		}
		return "redirect:/products";
	}
}
