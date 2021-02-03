package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerDTO;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogDTO {

    private OwnerDTO ownerDTO;
    private String name;

    @Builder
    public DogDTO(String name) {
        this.name = name;
    }

    public DogEntity toDogEntity(){
        return DogEntity.builder()
                .ownerEntity(ownerDTO.toOwnerEntity())
                .name(name)
                .build();
    }

}
