package com.ecommerce.products.productsmicroservice.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.products.productsmicroservice.model.Category;
import com.ecommerce.products.productsmicroservice.model.CategoryDetails;
import com.ecommerce.products.productsmicroservice.repository.CategoryRepository;
import com.ecommerce.products.productsmicroservice.services.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryRestController {
	
	@Autowired
	CategoryService categoryService;
	
	@RequestMapping(method = RequestMethod.GET,value="/categories")
	public ResponseEntity<List<Category>> getAllCategories() {
		try {
			List<Category> categories=categoryService.getAllCategories();
			if (categories != null) {
	            return ResponseEntity.status(HttpStatus.OK).body(categories);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/categories/{id}")
	public ResponseEntity<Category> getCategory(@PathVariable Long id) {
		try {
		Category category=categoryService.getCategory(id);
		if (category != null) {
            return ResponseEntity.status(HttpStatus.OK).body(category);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST,value="/categories")
	public String addCategories(@RequestBody CategoryDetails categorydetail) {
		try {
				categoryService.addCategory(new Category(categorydetail.getName(), categorydetail.getDescription()));
			    return "Success";
			}catch (Exception e) {
				return "Error";
	    }
	}
	
	@RequestMapping(method = RequestMethod.PUT,value="/categories")
	public String updateCategories(@RequestBody Category category) {
		try {
			categoryService.updateCategory(category);
		    return "Success";
		}catch (Exception e) {
			return "Error";
    }
	}
	
	@RequestMapping(method = RequestMethod.DELETE,value="/categories/{id}")
	public String deleteaddCategories(@PathVariable Long id) {
		try {
			categoryService.deleteCategory(id);
		    return "Success";
		}catch (Exception e) {
			return "Error";
    }
	}

	
}
