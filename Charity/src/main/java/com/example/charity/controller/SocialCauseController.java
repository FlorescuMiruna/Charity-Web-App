package com.example.charity.controller;

import com.example.charity.model.SocialCause;
import com.example.charity.service.SocialCauseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;

@RestController
@RequestMapping(value = "cause")
public class SocialCauseController {

  private final SocialCauseService socialCauseService;

  @Autowired
    public SocialCauseController(SocialCauseService socialCauseService) {
        this.socialCauseService = socialCauseService;
    }

    @GetMapping("")
    public  List<SocialCause> getAllSocialCauses(){
      return socialCauseService.getAllSocialCauses();
    }

  @GetMapping("/{id}")
  public  SocialCause getSocialCauseById(@PathVariable("id") Long id){
    return socialCauseService.getSocialCauseById(id);
  }
    @PostMapping("")
  public SocialCause saveSocialCause( @RequestBody SocialCause socialCause){
    return socialCauseService.saveSocialCause(socialCause);
    }
  @DeleteMapping(value = "/{id}")
  public  void deleteSocialCause(@PathVariable Long id){

    socialCauseService.deleteSocialCause(id);

  }
}
