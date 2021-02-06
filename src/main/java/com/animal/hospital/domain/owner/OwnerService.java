package com.animal.hospital.domain.owner;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerService {

    private final ModelMapper modelMapper;
    private final OwnerRepository ownerRepository;

    // Owner 등록
    public OwnerDTO register(OwnerDTO ownerDTO) {

        OwnerEntity owner = ownerDTO.toOwnerEntity();

        OwnerEntity ownerResult = ownerRepository.save(owner);

        return OwnerDTO.builder()
                .id(ownerResult.getId())
                .name(ownerResult.getName())
                .build();
    }

    public List<OwnerDTO> ownerFindAll() {
        List<OwnerEntity> ownerEntityList = ownerRepository.findAll();

        // List<OwnerEntity> -> List<OwnerDTO> Mapping
        return ownerEntityList.stream().map(p -> modelMapper.map(p, OwnerDTO.class)).collect(Collectors.toList());

    }


    public OwnerDTO ownerFind(String ownerName) {

        OwnerEntity ownerEntity = ownerRepository.findByName(ownerName).get();

        return modelMapper.map(ownerEntity, OwnerDTO.class);

    }
}
