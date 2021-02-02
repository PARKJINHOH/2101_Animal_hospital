package com.animal.hospital.domain;

import com.animal.hospital.domain.dog.DogEntity;
import com.animal.hospital.domain.dog.DogRepository;
import com.animal.hospital.domain.owner.OwnerEntity;
import com.animal.hospital.domain.owner.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
@Slf4j
public class DomainTest {

    @Autowired
    DogRepository dogRepository;

    @Autowired
    OwnerRepository ownerRepository;

    @AfterEach
    public void cleanup() {
        dogRepository.deleteAll();
        ownerRepository.deleteAll();
    }

    @Test
    public void 데이터_저장() throws Exception {

        // given
        OwnerEntity owner = new OwnerEntity();
        owner.setName("주인");

        DogEntity dog1 = new DogEntity();
        dog1.setName("강아지1");
        dog1.setOwnerEntity(owner);

        DogEntity dog2 = new DogEntity();
        dog2.setName("강아지2");
        dog2.setOwnerEntity(owner);

        ownerRepository.save(owner);
        dogRepository.save(dog1);
        dogRepository.save(dog2);

        // when
        System.out.println("---------------------");
        DogEntity dogEntity = dogRepository.findById(dog1.getId()).get();
        List<DogEntity> dogEntityList = dogEntity.getOwnerEntity().getDogList();

        // then
        for (DogEntity dogs : dogEntityList) {
            log.info("{} : {}", dogs.getOwnerEntity().getName(), dogs.getName());
            assertThat(dogEntityList.size()).isNotNull();

        }

    }

}
