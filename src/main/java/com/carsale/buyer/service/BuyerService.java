package com.carsale.buyer.service;

import com.carsale.buyer.model.Buyer;
import com.carsale.buyer.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BuyerService {

    @Autowired
    private BuyerRepository buyerRepository;

    public List<Buyer> getAllBuyers() {
        return buyerRepository.findAll();
    }

    public Optional<Buyer> getBuyerById(Long id) {
        return buyerRepository.findById(id);
    }

    public Buyer saveBuyer(Buyer buyer) {
        return buyerRepository.save(buyer);
    }

    public Buyer updateBuyer(Long id, Buyer buyerDetails) {
        Buyer buyer = buyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyer not found with id: " + id));

        buyer.setName(buyerDetails.getName());
        buyer.setEmail(buyerDetails.getEmail());
        buyer.setPhone(buyerDetails.getPhone());

        return buyerRepository.save(buyer);
    }

    public void deleteBuyer(Long id) {
        Buyer buyer = buyerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Buyer not found with id: " + id));

        buyerRepository.delete(buyer);
    }

    public Optional<Buyer> findByEmail(String email) {
        return buyerRepository.findByEmail(email);
    }

    public boolean authenticate(String email, String password) {
        Optional<Buyer> buyer = findByEmail(email);
        return buyer.isPresent() && buyer.get().getPassword().equals(password);
    }
}

