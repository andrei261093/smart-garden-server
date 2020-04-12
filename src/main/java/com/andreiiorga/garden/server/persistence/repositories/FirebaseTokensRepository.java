package com.andreiiorga.garden.server.persistence.repositories;

import com.andreiiorga.garden.server.persistence.entities.FirebaseTokenEntity;
import com.andreiiorga.garden.server.persistence.entities.PinEntity;
import com.andreiiorga.garden.server.persistence.entities.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class FirebaseTokensRepository {

    @Autowired
    EntityManager em;

    @Transactional
    public List<String> getTokensByUser(UserEntity user){
        List<FirebaseTokenEntity> tokens = em.createQuery("SELECT t FROM FirebaseTokenEntity t WHERE t.user = :id", FirebaseTokenEntity.class).setParameter("id", user.getId()).getResultList();
        List<String> returnList = new ArrayList<>();

        for(FirebaseTokenEntity token: tokens){
            returnList.add(token.getToken());
        }

        return returnList;
    }
}
