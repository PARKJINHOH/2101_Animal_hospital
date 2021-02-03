package com.animal.hospital.domain.owner;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerDTO {

    private String name;

    @Builder
    public OwnerDTO(String name) {
        this.name = name;
    }

    public OwnerEntity toOwnerEntity(){
        return OwnerEntity.builder()
                .name(name)
                .build();
    }
}
