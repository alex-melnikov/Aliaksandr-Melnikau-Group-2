package com.epam.jmp.api.impl;

import com.epam.jmp.api.SellerService;
import com.epam.jmp.data.Seller;
import com.epam.jmp.repository.SellerRepository;

import java.util.List;

public class SellerServiceImpl implements SellerService {

    private SellerRepository repository;

    public void setRepository(SellerRepository repository) {
        this.repository = repository;
    }

    /* (non-Javadoc)
     * @see org.shop.api.SellerService#getSellers()
     */
    @Override
    public List<Seller> getSellers() {
        return repository.getSellers();
    }

    /* (non-Javadoc)
     * @see org.shop.api.SellerService#getSellerById(java.lang.Long)
     */
    @Override
    public Seller getSellerById(Long sellerId) {
        return repository.getSellerById(sellerId);
    }

    /* (non-Javadoc)
     * @see org.shop.api.SellerService#importSellers(java.util.List)
     */
    @Override
    public void importSellers(List<Seller> sellers) {
        for (Seller seller : sellers) {
            repository.createOrUpdateSeller(seller);
        }
    }
}
