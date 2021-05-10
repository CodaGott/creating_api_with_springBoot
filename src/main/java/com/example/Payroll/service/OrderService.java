package com.example.Payroll.service;

import com.example.Payroll.exception.OrderException;
import com.example.Payroll.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> getAllOrders();

    boolean cancelOrder(Long orderId) throws OrderException;

    Order createOrder(Order order);

    void updateOrder(Long orderId);

    void findOrder(Long orderId);
}
