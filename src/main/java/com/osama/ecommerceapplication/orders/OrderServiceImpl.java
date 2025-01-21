package com.osama.ecommerceapplication.orders;

import com.osama.ecommerceapplication.exceptions.CustomExceptions;
import com.osama.ecommerceapplication.payments.Payment;
import com.osama.ecommerceapplication.payments.PaymentRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService{


    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderRepository.findById(orderId)
                .or(() -> {
                    throw new CustomExceptions.OrderNotFoundException(
                          orderId.toString()
                    );
                });
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    @Transactional
    public Order createOrder(Order order) {
        Payment payment = paymentRepository
                .findById(order.getPayment().getPaymentId())
                .orElseThrow(() -> new CustomExceptions.PaymentNotFoundException(
                        order.getPayment().getPaymentId().toString()
                ));

        order.setPayment(payment);
        return orderRepository.save(order);
    }

    @Override
    @Transactional
    public Order updateOrder(Order order) {
        Order existingOrder = orderRepository
                .findById(order.getOrderId())
                .orElseThrow(() -> new CustomExceptions.OrderNotFoundException(
                        String.format(order.getOrderId().toString())
                ));

        if (order.getEmail() == null || order.getOrderStatus() == null ||
                order.getTotalAmount() == null || order.getOrderItems() == null) {
            throw new CustomExceptions.InvalidOrderUpdateException(
                    "Order update failed due to missing required fields."
            );
        }

        existingOrder.setEmail(order.getEmail());
        existingOrder.setOrderStatus(order.getOrderStatus());
        existingOrder.setTotalAmount(order.getTotalAmount());
        existingOrder.setOrderItems(order.getOrderItems());

        return orderRepository.save(existingOrder);
    }

    @Override
    @Transactional
    public void deleteOrder(Long orderId) {
        Order existingOrder = orderRepository
                .findById(orderId)
                .orElseThrow(() -> new CustomExceptions.OrderNotFoundException(
                        orderId.toString()
                ));

        orderRepository.delete(existingOrder);
    }

    @Override
    public List<Order> getOrdersByStatus(OrderStatusValues orderStatus) {
        List<Order> orders = orderRepository.findByOrderStatus(orderStatus);
        if (orders.isEmpty()) {
            throw new CustomExceptions.OrderNotFoundException(
                  orderStatus.toString()
            );
        }
        return orders;
    }
}