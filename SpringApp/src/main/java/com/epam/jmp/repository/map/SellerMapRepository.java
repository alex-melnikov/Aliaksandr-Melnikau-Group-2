package com.epam.jmp.repository.map;

import com.epam.jmp.data.Seller;
import com.epam.jmp.repository.SellerRepository;

import java.util.ArrayList;
import java.util.List;

public final class SellerMapRepository extends AbstractMapRepository<Seller> implements SellerRepository {

    /* (non-Javadoc)
     * @see org.shop.repository.SellerRepository#createOrUpdate(org.shop.data.Seller)
     */
    @Override
    public void createOrUpdateSeller(Seller seller) {
        update(seller);
    }

    /* (non-Javadoc)
     * @see org.shop.repository.SellerRepository#getSellers()
     */
    @Override
    public List<Seller> getSellers() {
        return new ArrayList<Seller>(register.values());
    }

    /* (non-Javadoc)
     * @see org.shop.repository.SellerRepository#getSellerById(java.lang.Long)
     */
    @Override
    public Seller getSellerById(Long sellerId) {
        return get(sellerId);
    }
}
