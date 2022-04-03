package com.example.charity.controller;

import com.example.charity.model.Donation;
import com.example.charity.model.Event;
import com.example.charity.model.Organization;
import com.example.charity.service.DonationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/donation")
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


    @PostMapping(value = "/event/{eventId}")
    public Donation saveDonationWithEvent(@RequestBody Donation donation, @PathVariable Long eventId){
        donationService.saveDonationWithEvent(donation,eventId);
        return donation;

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

    @PutMapping("/{id}")
    public Donation updateDonation(@PathVariable Long id, @RequestBody Donation request) {

        Donation donation = donationService.updateDonation(id,request);
        return donation;
    }

    @PutMapping("/{donationId}/event/{eventId}")
    Donation assignEventToDonation(@PathVariable Long donationId, @PathVariable Long eventId) {


        return donationService.assignEventToDonation(donationId,eventId);

    }


}
