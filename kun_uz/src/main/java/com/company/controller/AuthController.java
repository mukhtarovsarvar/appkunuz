package com.company.controller;

import com.company.dto.AuthDto;
import com.company.dto.RegstrationDto;
import com.company.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

   // Logger log =  LoggerFactory.getLogger(AuthController.class); //  @Slf4j shu anotatsiya shunday ozgaruvchi ochib beradi


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid AuthDto dto){
        log.info("Authorization: {}",dto);
        return ResponseEntity.ok(authService.auth(dto));
    }


    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody @Valid RegstrationDto dto){
        authService.reg(dto);
        log.info("Registration: {}",dto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/verification/{jwt}")
    public ResponseEntity<?> verification(@PathVariable("jwt") String jwt){
        authService.verification(jwt);
        return ResponseEntity.ok().build();
    }





}
