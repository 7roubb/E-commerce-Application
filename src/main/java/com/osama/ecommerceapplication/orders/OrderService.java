package com.osama.ecommerceapplication.orders;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Optional<Order> getOrderById(Long orderId);

    List<Order> getAllOrders();

    Order createOrder(Order order);

    Order updateOrder(Order order);

    void deleteOrder(Long orderId);

    List<Order> getOrdersByStatus(OrderStatusValues orderStatus);
}
