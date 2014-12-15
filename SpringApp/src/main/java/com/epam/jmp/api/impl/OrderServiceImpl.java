package com.epam.jmp.api.impl;

import com.epam.jmp.api.ItemService;
import com.epam.jmp.api.OrderService;
import com.epam.jmp.data.Item;
import com.epam.jmp.data.Order;
import com.epam.jmp.data.Proposal;
import com.epam.jmp.data.User;
import com.epam.jmp.repository.OrderRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private ItemService itemService;

    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setItemService(ItemService itemService) {
        this.itemService = itemService;
    }

    @Override
    public Long createOrder(User user, Item... items) {
        //create and save order object
        Order order = new Order();
        order.setUser(user);
        order.setCreatedDate(new Date());

        Long orderId = orderRepository.createOrder(order);

        //save item objects
        for (Item item : items) {
            item.setOrder(order);
            itemService.createItem(item);
        }

        return orderId;
    }

    public Long createOrder(User user, Proposal... proposals) {
        List<Item> items = new ArrayList<Item>();

        for (Proposal proposal : proposals) {
            Item item = new Item();
            item.setProduct(proposal.getProduct());
            item.setPrice(proposal.getPrice());

            items.add(item);
        }

        return createOrder(user, items.toArray(new Item[items.size()]));
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.getOrderById(id);
    }

    @Override
    public void updateOrder(Order order) {
        orderRepository.updateOrder(order);
    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return getOrdersByUserId(user.getId());
    }

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.getOrdersByUserId(userId);
    }
}
