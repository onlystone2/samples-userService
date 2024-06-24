package com.kubeworks.userService.dao;

import com.kubeworks.userService.entity.Claim;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClaimDao extends MongoRepository<Claim, String> {

    Claim getClaimByClaimName(String claimName);
}
