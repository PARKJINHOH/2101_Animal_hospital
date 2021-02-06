package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import com.animal.hospital.domain.owner.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DogService {


    private final ModelMapper modelMapper;
    private final DogRepository dogRepository;
    private final OwnerRepository ownerRepository;

    // Dog 등록
    public DogDTO register(DogDTO dogDTO) {

        // OwnerEntity
        OwnerEntity owner = ownerRepository.findById(dogDTO.getOwner().getId()).get();

        DogEntity dog = dogDTO.toDogEntity(owner);

        DogEntity dogResult = dogRepository.save(dog);


        // todo : 현재는 임시로 이름을 동일하게 mapping 했지만 이름이 다르게 mapping 방법 찾기
        // map().setOwner(source.getOwner()); 에서 entity에 dto를 넣으려고 하기 때문에 error
        // https://baek.dev/post/15/
        /*
        PropertyMap<DogEntity, DogDTO> dogMap = new PropertyMap<DogEntity, DogDTO>() {
            @Override
            protected void configure() {
                map().setOwner(source.getOwner());
            }
        };
        */

        return modelMapper.map(dogResult, DogDTO.class);

    }
}
