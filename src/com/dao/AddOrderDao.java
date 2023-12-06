package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.model.Orders;
import com.tool.TextFunction;

public class AddOrderDao {
	ArrayList<Orders> orderarray = new ArrayList<Orders>();
	
	public AddOrderDao() {
		TextFunction txtfunc = new TextFunction("src/data/orders.txt");
		orderarray = txtfunc.readfile(Orders.class);
	}

	// view
        public Orders findDataByOrder(String id) {
		Orders findarray = this.orderarray.stream().filter(x->x.getOrderId().equals(id)).toList().get(0);
		return findarray;
	}
        public List<Orders> findDataByOrderMul(String id) {
		List<Orders> findarray = this.orderarray.stream().filter(x->x.getOrderId().equals(id)).toList();
		return findarray;
	}
	public List<Orders> findDataByCus(String id) {
		List<Orders> findarray = this.orderarray.stream().filter(x->x.getCustomerId().equals(id)).toList();
		return findarray;
	}
	public List<Orders> findDataByVen(String id) {
		List<Orders> findarray = this.orderarray.stream().filter(x->x.getVendorId().equals(id)).toList();
		return findarray;
	}
	public List<Orders> findDataByRun(String id) {
		List<Orders> findarray = this.orderarray.stream().filter(x->x.getRunnerId().equals(id)).toList();
		return findarray;
	}
	public List<Orders> findOrderData() {
		return this.orderarray;
	}
        public List<String> updateComboxOrd() {
	    return orderarray.stream()
	                   .map(Orders::getOrderId).distinct()
	                   .collect(Collectors.toList());
	}
        public List<String> updateComboxOrd(List<Orders> array) {
	    return array.stream()
	                   .map(Orders::getOrderId).distinct()
	                   .collect(Collectors.toList());
	}
	public List<String> updateComboxCus() {
	    return orderarray.stream()
	                   .map(Orders::getCustomerId).distinct()
	                   .collect(Collectors.toList());
	}
	public List<String> updateComboxVen() {
	    return orderarray.stream()
	                   .map(Orders::getVendorId).distinct()
	                   .collect(Collectors.toList());
	}
	public List<String> updateComboxRun() {
	    return orderarray.stream()
	                   .map(Orders::getRunnerId).distinct()
	                   .collect(Collectors.toList());
	}
	
}
