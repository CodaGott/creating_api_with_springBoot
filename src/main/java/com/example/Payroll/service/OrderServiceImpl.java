package com.example.Payroll.service;

import com.example.Payroll.exception.OrderException;
import com.example.Payroll.model.Order;
import com.example.Payroll.model.Status;
import com.example.Payroll.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public boolean cancelOrder(Long orderId) throws OrderException {
        Order order = orderRepository.findOrderById(orderId);

        if (order.getStatus().equals(Status.IN_PROGRESS)){
            return true;
        }else {
            throw new OrderException("You can't cancel completed or canceled order");
        }
    }

    @Override
    public Order createOrder(Order order) {
        return null;
    }

    @Override
    public void updateOrder(Long orderId) {

    }

    @Override
    public void findOrder(Long orderId) {

    }
}
