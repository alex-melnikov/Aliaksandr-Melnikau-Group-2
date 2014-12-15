package com.epam.jmp.api;

import com.epam.jmp.data.Seller;

import java.util.List;

/**
 * Provides API for manipulating selles.
 *
 * @author Dzmitry_Naskou
 */
public interface SellerService {

    /**
     * Import sellers.
     *
     * @param sellers the sellers
     */
    void importSellers(List<Seller> sellers);

    /**
     * Gets the sellers.
     *
     * @return the sellers
     */
    List<Seller> getSellers();

    /**
     * Gets the seller by id.
     *
     * @param sellerId the seller id
     * @return the seller by id
     */
    Seller getSellerById(Long sellerId);
}
