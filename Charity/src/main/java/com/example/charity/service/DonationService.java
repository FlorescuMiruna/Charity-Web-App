package com.example.charity.service;

import com.example.charity.exception.NotFoundException;
import com.example.charity.model.Donation;
import com.example.charity.model.SocialCause;
import com.example.charity.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    private final DonationRepository donationRepository;

    @Autowired
    public DonationService(DonationRepository donationRepository) {
        this.donationRepository = donationRepository;
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public Donation getDonationById(Long id) {
        Optional<Donation> optionalDonation = donationRepository.findById(id);
        return optionalDonation.orElseThrow(() -> new NotFoundException("Donation  not found!", "donation.not.found"));

    }


    public void deleteDonation(Long id) {
        Optional<Donation> donation = donationRepository.findById(id);
        if(donation.isPresent()){
            donationRepository.delete(donation.get());
        }
        else {
            throw new NotFoundException("Donation not found", "donation.not.found");
        }
    }
}
