package com.oms.bean;

import com.oms.bean.shipping.ShippingFee;
import com.oms.bean.shipping.implement.HNandHCMShippingFee;
import com.oms.bean.shipping.implement.OtherShippingFee;
import com.oms.serverapi.MediaApi;

import java.util.*;

public class Order {
	private String id;
	private String code;
	private String customerName;
	private String customerPhoneNumber;
	private String customerAddress;
	private ArrayList<OrderLine> orderLines;
	private float totalCost;
	
	public Order() {
		orderLines = new ArrayList<OrderLine>();
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public ArrayList<OrderLine> getOrderLines() {
		return orderLines;
	}

	public void setOrderLines(ArrayList<OrderLine> orderLines) {
		this.orderLines = orderLines;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}

	public float getTotalCost() {
		MediaApi api = new MediaApi();
		HashMap<String, String> query = new HashMap<>();
		float weight = 0;

		float res = 0;
		if (orderLines!= null) {
			Iterator<OrderLine> iter = orderLines.iterator();
			while (iter.hasNext()) {
				OrderLine ol = iter.next();
				res +=  ol.getProductCost() * ol.getProductQuantity();
				weight += ol.getProductQuantity() * ol.getWeight();

//				query.put("id", ol.getProductId());
//
//				ArrayList<Book> books;
//				if ((books = api.getBooks(query)).size() != 0) {
//					weight += books.get(0).getWeight()*ol.getProductQuantity();
//				} else {
//					ArrayList<CompactDisc> compactDiscs;
//					if ((compactDiscs = api.getCds(query)).size() != 0) {
//						weight += compactDiscs.get(0).getWeight()*ol.getProductQuantity();
//					} else {
//						ArrayList<DigitalVideoDisc> digitalVideoDiscs;
//						if ((digitalVideoDiscs = api.getDvds(query)).size() != 0) {
//							weight += digitalVideoDiscs.get(0).getWeight()*ol.getProductQuantity();
//						}
//					}
//				}
			}
		}
		if (weight<=0 || res<=0) return 0;
		if (res>=500000) return res;

		ShippingFee shippingFee;
		String address = customerAddress.toLowerCase();
		if (address.equals("hn") || address.equals("hà nội") || address.equals("HCM") || address.equals("hồ chí minh")) {
			shippingFee = new HNandHCMShippingFee();
		} else shippingFee = new OtherShippingFee();

		return res + shippingFee.calculate(weight);
	}
	
	
	
	public void addOrderLine(OrderLine orderLine) {
		boolean existed = false;
		for (OrderLine ol: orderLines) {
			if (ol.getProductId().equals(orderLine.getProductId())) {
				ol.setProductQuantity(ol.getProductQuantity() + orderLine.getProductQuantity());
				existed = true;
				break;
			}
		}
		
		if (!existed) {
			orderLines.add(orderLine);
		}
	}

	public boolean search(Order order) {
		if (this.id != null && !this.id.equals("") && !this.id.contains(order.id)) {
			return false;
		}
		if (this.code != null && !this.code.equals("") && !this.code.contains(order.code)) {
			return false;
		}
		if (this.customerName != null && !this.customerName.equals("") && !this.customerName.contains(order.customerName)) {
			return false;
		}
		if (this.totalCost != 0 && this.totalCost != order.totalCost) {
			return false;
		}
		return true;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Order) {
			return this.code.equals(((Order) obj).code);
		}
		return false;
	}
}