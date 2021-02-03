package com.animal.hospital.domain.owner;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final OwnerRepository ownerRepository;

    // Owner 등록
    public OwnerEntity register(OwnerDTO ownerDTO) {

        OwnerEntity owner = OwnerEntity.builder()
                .name(ownerDTO.getName())
                .build();

        return ownerRepository.save(owner);


    }


}
