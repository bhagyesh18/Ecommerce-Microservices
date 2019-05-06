package com.ecommerce.orders.ordersmicroservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.orders.ordersmicroservice.model.OrderDetail;
import com.ecommerce.orders.ordersmicroservice.repository.OrderDetailRepository;

@Service
public class OrderDetailService {

	@Autowired
	OrderDetailRepository orderDetailRepository;
	
	public List<OrderDetail> getAllOrderDetail(Long orderId){
		try {
	        List<OrderDetail> listOrderDetail=orderDetailRepository.findByOrderId(orderId);
			System.out.println(listOrderDetail.size());
	        return listOrderDetail;
		}catch (Exception e) {
			return null;
		}
	}
	
}
