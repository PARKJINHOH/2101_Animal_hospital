package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import com.animal.hospital.domain.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogSerivce {


    private final ModelMapper modelMapper;
    private final DogRepository dogRepository;
    private final OwnerRepository ownerRepository;

    // Dog 등록
    public DogDTO register(DogDTO dogDTO) {

        // OwnerEntity
        OwnerEntity dogOnwer = ownerRepository.findById(dogDTO.getOwnerEntity().getId()).get();

        DogEntity dog = DogEntity.builder()
                .name(dogDTO.getDogName())
                .ownerEntity(dogOnwer)
                .build();

        DogEntity dogResult = dogRepository.save(dog);

        return modelMapper.map(dogResult, DogDTO.class);

    }
}
