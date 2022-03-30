package com.example.charity.service;

import com.example.charity.exception.NotFoundException;
import com.example.charity.model.Donation;
import com.example.charity.model.SocialCause;
import com.example.charity.repository.SocialCouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Optional;

@Service
public class SocialCauseService {
    public final SocialCouseRepository socialCouseRepository;

    @Autowired
    public SocialCauseService(SocialCouseRepository socialCouseRepository) {
        this.socialCouseRepository = socialCouseRepository;
    }

    public List<SocialCause> getAllSocialCauses() {
        return socialCouseRepository.findAll();
    }

    public SocialCause saveSocialCause(SocialCause socialCause) {
        return socialCouseRepository.save(socialCause);
    }

    public SocialCause getSocialCauseById(Long id) {
        Optional<SocialCause> optionalSocialCause = socialCouseRepository.findById(id);

//        if (optionalSubject.isPresent()) {
//            return optionalSubject.get();
//        } else {
////            logger.warn("Subject not found!");
//            throw new RuntimeException("Subject not found!");
//        }

        return optionalSocialCause.orElseThrow(() -> new NotFoundException("Social cause not found!", "social.cause.not.found"));
    }

    public void deleteSocialCause(Long id) {
        Optional<SocialCause> socialCause = socialCouseRepository.findById(id);
        if(socialCause.isPresent()){
            socialCouseRepository.delete(socialCause.get());
        }
        else {
            throw new NotFoundException("Social cause not found", "social.cause.not.found");
        }
    }
}
