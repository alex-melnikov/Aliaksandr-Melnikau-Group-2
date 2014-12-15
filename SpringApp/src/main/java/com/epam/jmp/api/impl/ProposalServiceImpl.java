package com.epam.jmp.api.impl;

import com.epam.jmp.api.ProductService;
import com.epam.jmp.api.ProposalService;
import com.epam.jmp.api.SellerService;
import com.epam.jmp.data.Product;
import com.epam.jmp.data.Proposal;
import com.epam.jmp.data.Seller;
import com.epam.jmp.data.State;
import com.epam.jmp.repository.ProposalRepository;

import java.util.List;

public class ProposalServiceImpl implements ProposalService {

    private final ProposalRepository repository;

    private SellerService sellerService;

    private ProductService productService;

    public ProposalServiceImpl(ProposalRepository repository) {
        super();
        this.repository = repository;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProposalService#createProposal(java.lang.Long, java.lang.Long, java.lang.Double)
     */
    @Override
    public Long createProposal(Long sellerId, Long productId, Double price) {
        Proposal proposal = new Proposal();
        proposal.setPrice(price);
        proposal.setProduct(productService.getProductById(productId));
        proposal.setSeller(sellerService.getSellerById(sellerId));
        proposal.setState(State.NOT_ACTIVE_PROPOSAL);

        return repository.createProposal(proposal);
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProposalService#deactivateProposal(java.lang.Long)
     */
    @Override
    public void deactivateProposal(Long proposalId) {
        Proposal proposal = repository.getProposal(proposalId);
        proposal.setState(State.NOT_ACTIVE_PROPOSAL);

        repository.updateProposal(proposal);
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProposalService#activateProposal(java.lang.Long)
     */
    @Override
    public void activateProposal(Long proposalId) {
        Proposal proposal = repository.getProposal(proposalId);
        proposal.setState(State.ACTIVE_PROPOSAL);

        repository.updateProposal(proposal);
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProposalService#getProposalsByProduct(org.shop.data.Product)
     */
    @Override
    public final List<Proposal> getProposalsByProduct(Product product) {
        return getProposalsByProductId(product.getId());
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProposalService#getProposalsByProductId(java.lang.Long)
     */
    @Override
    public List<Proposal> getProposalsByProductId(Long productId) {
        return repository.getProposalsByProductId(productId);
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProposalService#getProposalsBySeller(org.shop.data.Seller)
     */
    @Override
    public final List<Proposal> getProposalsBySeller(Seller seller) {
        return getProposalsBySellerId(seller.getId());
    }

    /* (non-Javadoc)
     * @see org.shop.api.ProposalService#getProposalsBySellerId(java.lang.Long)
     */
    @Override
    public List<Proposal> getProposalsBySellerId(Long sellerId) {
        return repository.getProposalsBySellerId(sellerId);
    }
}
