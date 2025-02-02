package com.carsale.seller.service;

import com.carsale.seller.model.Seller;
import com.carsale.seller.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    public List<Seller> getAllSellers() {
        return sellerRepository.findAll();
    }

    public Optional<Seller> getSellerById(Long id) {
        return sellerRepository.findById(id);
    }

    public Seller saveSeller(Seller seller) {
        return sellerRepository.save(seller);
    }

    public Seller updateSeller(Long id, Seller sellerDetails) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + id));

        seller.setName(sellerDetails.getName());
        seller.setEmail(sellerDetails.getEmail());
        seller.setPhone(sellerDetails.getPhone());

        return sellerRepository.save(seller);
    }

    public void deleteSeller(Long id) {
        Seller seller = sellerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Seller not found with id: " + id));

        sellerRepository.delete(seller);
    }

    public Optional<Seller> findByEmail(String email) {
        return sellerRepository.findByEmail(email);
    }

    public boolean authenticate(String email, String password) {
        Optional<Seller> seller = findByEmail(email);
        return seller.isPresent() && seller.get().getPassword().equals(password);
    }
}

