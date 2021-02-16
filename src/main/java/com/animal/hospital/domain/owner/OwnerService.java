package com.animal.hospital.domain.owner;

import lombok.RequiredArgsConstructor;
import org.hibernate.collection.spi.PersistentCollection;
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

    public List<OwnerDTO> ownerFindAll(String name) {
        List<OwnerEntity> ownerEntityList;

        if (name == null) {
            ownerEntityList = ownerRepository.findAll();
        } else {
            ownerEntityList = ownerRepository.findAllByName(name);
        }

        modelMapper.getConfiguration().setPropertyCondition(
                context -> !(context.getSource() instanceof PersistentCollection));

        // List<OwnerEntity> -> List<OwnerDTO> Mapping
        return ownerEntityList.stream().map(p -> modelMapper.map(p, OwnerDTO.class)).collect(Collectors.toList());

    }


    public OwnerDTO ownerFindId(Long ownerId) {

        OwnerEntity ownerEntity = ownerRepository.findById(ownerId).get();

        return modelMapper.map(ownerEntity, OwnerDTO.class);

    }

    public void delete(Long id) {
        ownerRepository.deleteById(id);
    }
}
