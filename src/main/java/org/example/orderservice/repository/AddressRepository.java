package org.example.orderservice.repository;

import org.example.orderservice.model.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Long> {
    Optional<List<AddressEntity>> findAddressEntitiesByUserId(Long userId);

    Optional<AddressEntity> findAddressEntitiesById(Long id);
}
