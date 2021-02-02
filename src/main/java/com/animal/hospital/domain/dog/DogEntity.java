package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class DogEntity {

    @Id @GeneratedValue
    @Column(name = "DOG_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "OWNER_ID")
    private OwnerEntity ownerEntity;
}
