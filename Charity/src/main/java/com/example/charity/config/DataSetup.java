package com.example.charity.config;

import com.example.charity.model.auth.ERole;
import com.example.charity.model.auth.Role;
import com.example.charity.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;


import java.time.LocalDate;

@Component
@ConditionalOnExpression("${insert.start.data}")
public class DataSetup implements ApplicationRunner {

    private final RoleRepository roleRepository;

    @Autowired
    public DataSetup(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        saveRole(ERole.ROLE_USER);
        saveRole(ERole.ROLE_ADMIN);


    }

    private void saveRole(ERole name) {
        Role role = new Role();
        role.setName(name);

        roleRepository.save(role);
    }

}