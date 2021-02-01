package com.animal.hospital.domain.owner;

import com.animal.hospital.domain.dog.DogEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OwnerEntity {

    @Id @GeneratedValue
    @Column(name = "OWNER_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ownerEntity")
    private List<DogEntity> dogList = new ArrayList<>();



}
