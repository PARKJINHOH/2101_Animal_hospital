package com.animal.hospital.domain.dog;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DogRepository extends JpaRepository<DogEntity, Long> {
}
