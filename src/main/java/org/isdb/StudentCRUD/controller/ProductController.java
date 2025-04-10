package org.isdb.StudentCRUD.controller;

import org.isdb.StudentCRUD.model.Product;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

	@PostMapping("/save")
	public String saveProduct(@Valid @RequestBody Product product) {
		log.info(product.toString());
		return "Saved Product";
	}

}
