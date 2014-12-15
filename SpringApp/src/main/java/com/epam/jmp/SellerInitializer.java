package com.epam.jmp;

import com.epam.jmp.api.SellerService;
import com.epam.jmp.data.Seller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


/**
 * The Seller Initializer util class.
 */
public class SellerInitializer {

    /**
     * The seller service.
     */
    private SellerService sellerService;

    /**
     * The seller names.
     */
    private Map<Long, String> sellerNames = Collections.emptyMap();

    /**
     * @param sellerNames the map of sellers
     */
    public SellerInitializer(Map<Long, String> sellerNames) {
        super();
        this.sellerNames = sellerNames;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    /**
     * Inits the sellers.
     */
    public void initSellers() {
        List<Seller> sellers = new LinkedList<Seller>();

        for (Map.Entry<Long, String> entry : sellerNames.entrySet()) {
            Seller seller = new Seller();
            seller.setId(entry.getKey());
            seller.setName(entry.getValue());

            sellers.add(seller);
        }

        sellerService.importSellers(sellers);
    }
}
