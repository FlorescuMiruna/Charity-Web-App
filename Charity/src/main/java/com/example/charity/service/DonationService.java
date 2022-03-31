package com.example.charity.service;

import com.example.charity.exception.NotFoundException;
import com.example.charity.model.Donation;
import com.example.charity.model.Event;
import com.example.charity.model.SocialCause;
import com.example.charity.repository.DonationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DonationService {

    private final DonationRepository donationRepository;
    private final EventService eventService;

    @Autowired
    public DonationService(DonationRepository donationRepository, EventService eventService) {
        this.donationRepository = donationRepository;
        this.eventService = eventService;
    }

    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    public Donation saveDonationWithEvent(Donation donation, Long eventId) {
        Event event = eventService.getEventById(eventId);
        event.setTotalAmountOfMoney(event.getTotalAmountOfMoney() + donation.getAmountOfMoney());
        donation.setEvent(event);
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
    public Donation updateDonation(Long id, Donation donationUpdated) {
        Optional<Donation> donationOptional = donationRepository.findById(id);

        if (donationOptional.isPresent()) {
            donationUpdated.setId(id);
            donationUpdated.setAmountOfMoney(donationUpdated.getAmountOfMoney() == null ? donationOptional.get().getAmountOfMoney() : donationUpdated.getAmountOfMoney());
            donationUpdated.setDontaionDate(donationUpdated.getDontaionDate() == null ? donationOptional.get().getDontaionDate() : donationUpdated.getDontaionDate());
            return donationRepository.save(donationUpdated);
        } else {
            throw new NotFoundException("Event not found!", "event.not.found");
        }
    }

    public Donation saveDonation(Donation donation) {
        return donationRepository.save(donation);
    }

    public Donation assignEventToDonation(Long donationId, Long eventId) {
        Donation donation = getDonationById(donationId);
        Event event = eventService.getEventById(eventId);
        donation.setEvent(event);
        return donationRepository.save(donation);
    }
}
