package com.animal.hospital.domain.dog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogSerivce {

    private final DogRepository dogRepository;

    // Dog 등록
    public void register(DogDTO dogDTO) {

        DogEntity dog = DogEntity.builder()
                .name(dogDTO.getName())
                .ownerEntity(dogDTO.getOwnerDTO().toOwnerEntity())
                .build();

        dogRepository.save(dog);
    }
}
