package com.andreiiorga.garden.server.persistence.repositories;

import com.andreiiorga.garden.server.persistence.entities.views.UserActivePinsViewEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class ViewsRepository {

    @Autowired
    EntityManager em;

    @Transactional
    public List<UserActivePinsViewEntity> getActivePinsForUserId(int userId){
        return em.createQuery("SELECT v FROM UserActivePinsViewEntity v WHERE v.userId = :userId", UserActivePinsViewEntity.class).setParameter("userId", userId).getResultList();
    }
}
