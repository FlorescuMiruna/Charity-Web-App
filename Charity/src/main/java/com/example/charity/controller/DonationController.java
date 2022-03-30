package com.example.charity.controller;

import com.example.charity.model.Donation;
import com.example.charity.service.DonationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/donation")
public class DonationController {

    private final DonationService donationService;

    private Logger logger = LoggerFactory.getLogger(DonationController.class);

    @Autowired
    public DonationController(DonationService donationService) {
        this.donationService = donationService;
    }

    @GetMapping(value = "")
    public List<Donation> getAllDonations(){
        return donationService.getAllDonations();
    }
    @GetMapping("/{id}")
    public Donation getDonationById(@PathVariable("id") Long id){
        return donationService.getDonationById(id);
    }
    @PostMapping(value = "")
    public Donation saveDonation(@RequestBody Donation donation){
        donationService.saveDonation(donation);
        return donation;

    }

    @DeleteMapping(value = "/{id}")
    public  void deleteDonation(@PathVariable Long id){


        logger.info("Deleted donation with {}", id);
        donationService.deleteDonation(id);

    }
}
