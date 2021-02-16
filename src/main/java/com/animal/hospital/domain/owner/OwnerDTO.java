package com.animal.hospital.domain.owner;

import com.animal.hospital.domain.dog.DogDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OwnerDTO {

    private Long id;
    private String name;
    private List<DogDTO> dogList;

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
