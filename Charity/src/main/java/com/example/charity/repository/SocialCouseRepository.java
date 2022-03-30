package com.example.charity.repository;

import com.example.charity.model.SocialCause;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SocialCouseRepository extends JpaRepository<SocialCause,Long> {
}
