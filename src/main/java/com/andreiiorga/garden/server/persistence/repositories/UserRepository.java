package com.andreiiorga.garden.server.persistence.repositories;

import com.andreiiorga.garden.server.persistence.entities.UserEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserRepository {

    @Autowired
    EntityManager em;

    @Transactional
    public UserEntity findById(int id){
        Session session = em.unwrap(Session.class);
        return (UserEntity) session.get(UserEntity.class, id);
    }

    @Transactional
    public List<UserEntity> findAll(){
        return em.createQuery("SELECT a FROM UserEntity a", UserEntity.class).getResultList();
    }
}
