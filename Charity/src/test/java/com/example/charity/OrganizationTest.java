package com.example.charity;

import com.example.charity.model.Organization;
import com.example.charity.repository.OrganizationRepository;
import com.example.charity.service.OrganizationService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.security.auth.Subject;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrganizationTest {

    @InjectMocks
    private OrganizationService organizationService;

    @Mock
    private OrganizationRepository organizationRepository;

    @Test
    @DisplayName("Get organization from db")
    void test_getOrganization() {
        // Arrange
        Long id = 1L;
        Organization organization = new Organization();
        organization.setId(id);
        when(organizationRepository.findById(id)).thenReturn(Optional.of(organization));

        // Act
        Organization actualOrganization = organizationService.getOrganizationById(id);

        // Assert
        assertEquals(id, actualOrganization.getId());
        verify(organizationRepository, times(1)).findById(id);
    }
}
