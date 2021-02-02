package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
public class DogEntity {

    @Id @GeneratedValue
    @Column(name = "DOG_ID")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private OwnerEntity ownerEntity;


    @Builder
    public DogEntity(String name, OwnerEntity ownerEntity) {
        this.name = name;
        this.ownerEntity = ownerEntity;
    }
}
