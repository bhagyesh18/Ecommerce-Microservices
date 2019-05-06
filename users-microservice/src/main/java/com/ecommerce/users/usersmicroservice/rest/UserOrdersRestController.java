package com.ecommerce.users.usersmicroservice.rest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.users.usersmicroservice.model.OrderDetail;
import com.ecommerce.users.usersmicroservice.model.Orders;
import com.ecommerce.users.usersmicroservice.model.User;
import com.ecommerce.users.usersmicroservice.model.UserOrderDetail;
import com.ecommerce.users.usersmicroservice.model.UserOrders;
import com.ecommerce.users.usersmicroservice.service.UserServices;

@RestController
@RequestMapping("/api")
public class UserOrdersRestController {


	@Autowired
	UserServices userService;
	
	@Autowired
	RestTemplate restTemplate;
	
	
	@RequestMapping(method = RequestMethod.GET,value="/users/{userId}/orders")
	public ResponseEntity<List<UserOrders>> getAllOrderOfUser(@PathVariable(name = "userId")Long userId) {
		try {
			ResponseEntity<List<Orders>> listOrders=restTemplate.exchange("http://orders-microservice/api/orders/user/"+userId, HttpMethod.GET, null, new ParameterizedTypeReference<List<Orders>>() {});	
			if (listOrders != null) {
				List<UserOrders> listUserOrder=new ArrayList<UserOrders>();

				for(Orders order:listOrders.getBody()) {
					ResponseEntity<List<OrderDetail>> listOrderDetail=restTemplate.exchange("http://orders-microservice/api/orders/"+order.getId()+"/products", HttpMethod.GET, null, new ParameterizedTypeReference<List<OrderDetail>>() {});	
					Set<UserOrderDetail> userorderDetail=new HashSet<UserOrderDetail>(); 
					for (OrderDetail  orderDetail : listOrderDetail.getBody()) {
						 userorderDetail.add(new UserOrderDetail(orderDetail.getProduct().getName(), orderDetail.getQuantity()));
					} 		
					
					listUserOrder.add(new UserOrders(order.getOrderDate(), order.getTotal(), order.getTotal(),userorderDetail));
				}
				return ResponseEntity.status(HttpStatus.OK).body(listUserOrder);
			
			} else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
	}
}
