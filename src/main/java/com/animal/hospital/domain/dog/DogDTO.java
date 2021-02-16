package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerDTO;
import com.animal.hospital.domain.owner.OwnerEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DogDTO {

    private Long id;
    private String dogName;
    private OwnerDTO owner;

    @Builder
    public DogDTO(Long id, String dogName, OwnerDTO owner) {
        this.id = id;
        this.dogName = dogName;
        this.owner = owner;
    }

    public DogEntity toDogEntity(OwnerEntity owner) {
        return DogEntity.builder()
                .owner(owner)
                .name(dogName)
                .build();
    }
}
