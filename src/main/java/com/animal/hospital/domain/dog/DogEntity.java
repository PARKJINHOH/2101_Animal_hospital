package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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

    private String name;

    @JsonIgnoreProperties({"dog"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private OwnerEntity owner;


    @Builder
    public DogEntity(String name, OwnerEntity owner) {
        this.name = name;
        this.owner = owner;
    }
}
