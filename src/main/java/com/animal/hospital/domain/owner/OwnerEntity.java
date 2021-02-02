package com.animal.hospital.domain.owner;

import com.animal.hospital.domain.dog.DogEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class OwnerEntity {

    @Id
    @GeneratedValue
    @Column(name = "OWNER_ID")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "ownerEntity", fetch = FetchType.EAGER)
    private List<DogEntity> dogList = new ArrayList<>();
}
