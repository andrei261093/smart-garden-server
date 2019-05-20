package com.andreiiorga.garden.server.persistence.repositories;

import com.andreiiorga.garden.server.persistence.entities.DeviceEntity;
import com.andreiiorga.garden.server.persistence.entities.UserEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DeviceRepository {

    @Autowired
    EntityManager em;

    @Autowired
    UserRepository userRepository;

    @Transactional
    public DeviceEntity findById(int id){
        Session session = em.unwrap(Session.class);
        return (DeviceEntity) session.get(DeviceEntity.class, id);
    }

    @Transactional
    public List<DeviceEntity> findByUser(int userId){
        UserEntity userEntity = userRepository.findById(userId);
        return em.createQuery("SELECT d FROM DeviceEntity d LEFT JOIN FETCH d.user WHERE d.user = :user", DeviceEntity.class).setParameter("user", userEntity).getResultList();
    }

    public DeviceEntity findByCustomId(String deviceCustomId) {
        return em.createQuery("SELECT d FROM DeviceEntity d WHERE d.topic = :deviceCustomId", DeviceEntity.class).setParameter("deviceCustomId", deviceCustomId).getSingleResult();
    }
}
