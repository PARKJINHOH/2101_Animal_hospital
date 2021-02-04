package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DogDTO {

    private Long id;
    private String dogName;
    private OwnerEntity ownerEntity;

    @Builder
    public DogDTO(Long id, String dogName, OwnerEntity ownerEntity) {
        this.id = id;
        this.dogName = dogName;
        this.ownerEntity = ownerEntity;
    }

}
