package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.http.HttpStatusEnum;
import com.animal.hospital.domain.http.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/dog")
public class DogController {

    private final DogService dogService;


    @PostMapping()
    public ResponseEntity<Message> register(@RequestBody DogDTO dogDTO) {

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

        try {
            DogDTO dogResult = dogService.register(dogDTO);

            message.setData(dogResult);
            message.setStatus(HttpStatusEnum.OK);
            message.setMessage("SUCCESS");

            return new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            message.setMessage(e.getMessage());
            return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        ResponseEntity<String> entity;

        try {
            dogService.delete(id);
            entity = new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage());
            entity = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        return entity;

    }


    // todo : 조회


}

