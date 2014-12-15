package com.epam.jmp.api.impl;

import com.epam.jmp.api.ItemService;
import com.epam.jmp.data.Item;
import com.epam.jmp.repository.ItemRepository;

import java.util.List;

public class ItemServiceImpl implements ItemService {

    /**
     * The item repository.
     */
    private final ItemRepository repository;

    /**
     * Instantiates a new item service impl.
     *
     * @param repository the item repository
     */
    public ItemServiceImpl(ItemRepository repository) {
        super();
        this.repository = repository;
    }

    /* (non-Javadoc)
     * @see org.shop.api.impl.ItemService#createItem(org.shop.data.Item)
     */
    @Override
    public Long createItem(Item item) {
        return repository.createItem(item);
    }

    /* (non-Javadoc)
     * @see org.shop.api.impl.ItemService#deleteItem(java.lang.Long)
     */
    @Override
    public void deleteItem(Long itemId) {
        repository.deleteItem(itemId);
    }

    /* (non-Javadoc)
     * @see org.shop.api.impl.ItemService#getItemsByOrderId(java.lang.Long)
     */
    @Override
    public List<Item> getItemsByOrderId(Long orderId) {
        return repository.getItemsByOrderId(orderId);
    }
}
