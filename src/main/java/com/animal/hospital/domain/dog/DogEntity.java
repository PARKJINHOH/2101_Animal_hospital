package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class DogEntity {

    @Id @GeneratedValue
    @Column(name = "DOG_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private OwnerEntity ownerEntity;


    @Builder
    public DogEntity(String name, OwnerEntity ownerEntity) {
        this.name = name;
        this.ownerEntity = ownerEntity;
    }
}
