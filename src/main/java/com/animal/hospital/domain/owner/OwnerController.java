package com.animal.hospital.domain.owner;

import com.animal.hospital.domain.http.HttpStatusEnum;
import com.animal.hospital.domain.http.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/owner")
public class OwnerController {

    private final OwnerService ownerService;

    // 회원 등록
    @PostMapping()
    public ResponseEntity<Message> ownerRegister(@RequestBody OwnerDTO ownerDTO) {

        Message message = new Message();

        try {
            OwnerDTO ownerResult = ownerService.register(ownerDTO);

            message.setData(ownerResult);
            message.setStatus(HttpStatusEnum.OK);
            message.setMessage("SUCCESS");

            return new ResponseEntity<>(message, jsonHeaders(), HttpStatus.CREATED);
        } catch (Exception e) {
            message.setMessage("FAIL : " + e.getMessage());
            return new ResponseEntity<>(message, jsonHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    // 모든 회원 조회
    @GetMapping()
    public ResponseEntity<Message> ownerFindAll() {

        Message message = new Message();

        try {
            List<OwnerDTO> ownerDTOS = ownerService.ownerFindAll();

            message.setData(ownerDTOS);
            message.setStatus(HttpStatusEnum.OK);
            message.setMessage("SUCCESS");

            return new ResponseEntity<>(message, jsonHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            message.setMessage("FAIL : " + e.getMessage());
            return new ResponseEntity<>(message, jsonHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    // 단일 회원 조회
    @GetMapping("/{ownerName}")
    public ResponseEntity<Message> ownerFind(@PathVariable String ownerName){
        Message message = new Message();

        try {
            OwnerDTO ownerDTO = ownerService.ownerFind(ownerName);

            message.setData(ownerDTO);
            message.setStatus(HttpStatusEnum.OK);
            message.setMessage("SUCCESS");

            return new ResponseEntity<>(message, jsonHeaders(), HttpStatus.OK);
        } catch (Exception e) {
            message.setMessage("FAIL : " + e.getMessage());
            return new ResponseEntity<>(message, jsonHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    private HttpHeaders jsonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
        return headers;
    }

}
