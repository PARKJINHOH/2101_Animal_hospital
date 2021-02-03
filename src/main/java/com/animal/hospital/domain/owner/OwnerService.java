package com.animal.hospital.domain.owner;

import com.animal.hospital.domain.dog.DogDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    // Owner 등록
    public OwnerDTO register(OwnerDTO ownerDTO) {

        OwnerEntity owner = OwnerEntity.builder()
                .name(ownerDTO.getName())
                .build();

        OwnerEntity ownerResult = ownerRepository.save(owner);

        return OwnerDTO.builder()
                .id(ownerResult.getId())
                .name(ownerResult.getName())
                .build();
    }

    // Onwer 찾기
    public OwnerDTO findOwner(DogDTO dogDTO) {
        OwnerEntity findOwner = ownerRepository.findById(dogDTO.getId()).get();
        return OwnerDTO.builder()
                .id(findOwner.getId())
                .name(findOwner.getName())
                .build();
    }


}
