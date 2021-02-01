package com.animal.hospital.domain.owner;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<OwnerEntity,Long> {
    
}
