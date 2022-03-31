package com.example.charity.service;

import com.example.charity.exception.NotFoundException;


import com.example.charity.model.Event;
import com.example.charity.model.Organization;

import com.example.charity.model.SocialCause;
import com.example.charity.repository.OrganizationRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.Subject;
import java.util.List;
import java.util.Optional;

@Service
public class OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final SocialCauseService socialCauseService;

    @Autowired
    public OrganizationService(OrganizationRepository organizationRepository, SocialCauseService socialCauseService) {
        this.organizationRepository = organizationRepository;
        this.socialCauseService = socialCauseService;
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization saveOrganization(Organization organization) {


        return organizationRepository.save(organization);
    }

    public Organization getOrganizationById(Long id) {
        Optional<Organization> optionalOrganization = organizationRepository.findById(id);
        return optionalOrganization.orElseThrow(() -> new NotFoundException("Organization  not found!", "organization.not.found"));

    }


    public void deleteOrganization(Long id) {
        Optional<Organization> organization = organizationRepository.findById(id);
        if(organization.isPresent()){
            organizationRepository.delete(organization.get());
        }
        else {
            throw new NotFoundException("Organization not found", "organization.not.found");
        }
    }

    public Organization assignSocialCauseToOrganization(Long organizationId, Long socialCauseId) {
        Organization organization = getOrganizationById(organizationId);
        SocialCause socialCause = socialCauseService.getSocialCauseById(socialCauseId);
        organization.getSocialCauses().add(socialCause);
        return organizationRepository.save(organization);
    }

    public Organization updateOrganization(Long id, Organization organizationUpdated) {
        Optional<Organization> organizationOptional = organizationRepository.findById(id);

        if (organizationOptional.isPresent()) {
            organizationUpdated.setId(id);
            organizationUpdated.setName(organizationUpdated.getName() == null ? organizationOptional.get().getName() : organizationUpdated.getName());
            organizationUpdated.setDescription(organizationUpdated.getDescription() == null ? organizationOptional.get().getDescription() : organizationUpdated.getDescription());
            organizationUpdated.setFoundingDate(organizationUpdated.getFoundingDate() == null ? organizationOptional.get().getFoundingDate() : organizationUpdated.getFoundingDate());

            return organizationRepository.save(organizationUpdated);
        } else {
            throw new NotFoundException("Organization not found!", "organization.not.found");
        }
    }



}
