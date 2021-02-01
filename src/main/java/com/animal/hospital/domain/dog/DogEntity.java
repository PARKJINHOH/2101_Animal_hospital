package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;

import javax.persistence.*;

@Entity
public class DogEntity {

    @Id @GeneratedValue
    @Column(name = "DOG_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private OwnerEntity ownerEntity;
}
