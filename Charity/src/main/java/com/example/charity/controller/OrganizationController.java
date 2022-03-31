package com.example.charity.controller;

import com.example.charity.model.Organization;
import com.example.charity.service.OrganizationService;
import com.example.charity.service.SocialCauseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.Subject;
import java.util.List;

@RestController
@RequestMapping("/organization")
public class OrganizationController {
    public final OrganizationService organizationService;
    public final SocialCauseService socialCauseService;
//    public final OrganizationRepository organizationRepository;
//    public final SocialCouseRepository socialCouseRepository;
    private Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    public OrganizationController(OrganizationService organizationService, SocialCauseService socialCauseService) {
        this.organizationService = organizationService;

        this.socialCauseService = socialCauseService;
    }

    @GetMapping(value = "")
    public List<Organization> getAllOrganizations(){

        return organizationService.getAllOrganizations();
    }

    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable("id") Long id){
        return organizationService.getOrganizationById(id);
    }

    @PostMapping(value = "")
    public Organization saveOrganization(@RequestBody Organization organization){
        organizationService.saveOrganization(organization);
        return organization;

    }



    @DeleteMapping(value = "/{id}")
    public  void deleteOrganization(@PathVariable Long id){
        logger.info("Deleted organization with {}", id);
        organizationService.deleteOrganization(id);

    }
    @PutMapping("/{organizationId}/social-cause/{socialCauseId}")
    Organization assignSocialCauseToOrganization (@PathVariable Long organizationId, @PathVariable Long socialCauseId){

      Organization organization = organizationService.assignSocialCauseToOrganization(organizationId,socialCauseId);
      return organization;
    }
//    @PutMapping("/{teacherId}/subject/{subjectId}")
//    public TeacherDto assignSubjectToTeacher(@PathVariable(name = "teacherId") Long teacherId,
//                                             @PathVariable(name = "subjectId") Long subjectId) {
//        Teacher teacher = teacherService.assignSubjectToTeacher(teacherId, subjectId);
//        return teacherConverter.maptoDto(teacher);
//    }
//
//
//    @PutMapping("/{subjectId}/students/{studentId}")
//    Subject addStudentToSubject(@PathVariable Long subjectId, @PathVariable Long studentId)
//    {
//        Subject subject = subjectRepository.findById(subjectId).get();
//        Student student = studentRepository.findById(studentId).get();
//        subject.enrolledStudents.add(student);
//        return subjectRepository.save(subject);
//
//    }


}
