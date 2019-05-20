package com.andreiiorga.garden.server.persistence.repositories;

import com.andreiiorga.garden.server.persistence.entities.DeviceEntity;
import com.andreiiorga.garden.server.persistence.entities.PinEntity;
import com.andreiiorga.garden.server.persistence.entities.UserEntity;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class PinRepository {

    @Autowired
    EntityManager em;

    @Autowired
    DeviceRepository deviceRepository;

    @Transactional
    public PinEntity findById(int id){
       return em.createQuery("SELECT p FROM PinEntity p LEFT JOIN FETCH p.device WHERE p.id = :id", PinEntity.class).setParameter("id", id).getSingleResult();
    }

    @Transactional
    public List<PinEntity> findByDevice(int deviceId){
        DeviceEntity deviceEntity = deviceRepository.findById(deviceId);
        return em.createQuery("SELECT p FROM PinEntity p LEFT JOIN FETCH p.device WHERE p.device = :device", PinEntity.class).setParameter("device", deviceEntity).getResultList();
    }

    @Transactional
    public PinEntity findByDeviceCustomIdAndNumberOnDevice(String deviceCustomId, int numberOnDevice){
        DeviceEntity deviceEntity = deviceRepository.findByCustomId(deviceCustomId);
        return em.createQuery("SELECT p FROM PinEntity p LEFT JOIN FETCH p.device WHERE p.device = :device AND p.numberOnDevice = :numberOnDevice", PinEntity.class).setParameter("device", deviceEntity).setParameter("numberOnDevice", numberOnDevice).getSingleResult();
    }

    @Transactional
    public void save(PinEntity pinEntity) {
        em.merge(pinEntity);
    }
}
