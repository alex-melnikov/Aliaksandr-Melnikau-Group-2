package com.epam.jmp.repository;

import com.epam.jmp.data.Item;

import java.util.List;

/**
 * Provides repository for manipulating items.
 *
 * @author Dzmitry_Naskou
 */
public interface ItemRepository {

    /**
     * Creates the item.
     *
     * @param item the item
     * @return the item id
     */
    Long createItem(Item item);

    /**
     * Delete item.
     *
     * @param itemId the item id
     */
    void deleteItem(Long itemId);

    /**
     * Gets the items by order id.
     *
     * @param orderId the order id
     * @return the items by order id
     */
    List<Item> getItemsByOrderId(Long orderId);
}
