package com.animal.hospital.domain.owner;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class OwnerDTO {

    private Long id;
    private String name;

    @Builder
    public OwnerDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public OwnerEntity toOwnerEntity(){
        return OwnerEntity.builder()
                .name(name)
                .build();
    }
}
