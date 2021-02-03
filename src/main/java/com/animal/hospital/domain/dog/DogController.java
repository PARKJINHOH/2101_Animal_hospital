package com.animal.hospital.domain.dog;

import com.animal.hospital.domain.http.HttpStatusEnum;
import com.animal.hospital.domain.http.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.Charset;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dog")
public class DogController {

    private final DogSerivce dogSerivce;

    // Dog 등록
    @PostMapping("")
    public ResponseEntity<Message> register(@RequestBody DogDTO dogDTO) {

        Message message = new Message();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));

        try {
            message.setData(dogDTO);

            dogSerivce.register(dogDTO);

            message.setStatus(HttpStatusEnum.OK);
            message.setMessage("SUCCESS");

            return new ResponseEntity<>(message, headers, HttpStatus.CREATED);
        } catch (Exception e) {
            message.setMessage(e.getMessage());
            return new ResponseEntity<>(message, headers, HttpStatus.BAD_REQUEST);
        }
    }


    // 조회


}
