package com.kaankaplan.userService;


import com.kaankaplan.userService.entity.Claim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClaimDataLoader implements ApplicationRunner {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        // Load initial data here

        List<Claim> result = mongoTemplate.find(
                Query.query(Criteria.where("claimName").is("CUSTOMER")),
                Claim.class);

        if (result.isEmpty()) {

            Claim claim = Claim.builder().claimName("CUSTOMER").build();
            mongoTemplate.insert(claim);
        }
    }

}
