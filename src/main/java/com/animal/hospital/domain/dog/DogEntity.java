package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.owner.OwnerEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DogEntity {

    @Id @GeneratedValue
    @Column(name = "DOG_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_ID")
    private OwnerEntity ownerEntity;
}
