package com.animal.hospital.domain.owner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends JpaRepository<OwnerEntity, Long> {

    Optional<OwnerEntity> findByName(String ownerName);

    List<OwnerEntity> findAllByName(String name);
}
