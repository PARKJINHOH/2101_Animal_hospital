package com.animal.hospital.domain.owner;

import com.animal.hospital.domain.dog.DogEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@RequiredArgsConstructor
public class OwnerEntity {

    @Id
    @GeneratedValue
    @Column(name = "OWNER_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "owner")
    private List<DogEntity> dogList = new ArrayList<>();

    @Builder
    public OwnerEntity(String name) {
        this.name = name;
    }
}
