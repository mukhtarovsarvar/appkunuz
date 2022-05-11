package com.company.controller;


import com.company.dto.RegionDto;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.RegionService;
import com.company.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody @Valid RegionDto dto,
                                    HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.create(pId, dto));
    }

    @DeleteMapping("/adm/{key}")
    public ResponseEntity<?> deleteByKey(@PathVariable("key") String key,
                                         HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.delete(key));
    }

    @PutMapping("/adm/update/{key}")
    public ResponseEntity<?> updateByKey(@PathVariable("key")  String key,
                                         @RequestBody @Valid RegionDto dto,
                                         HttpServletRequest request){
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.update(key, dto));
    }
    @GetMapping("/adm/list")
    public ResponseEntity<?> getList(HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.getList());
    }

    @GetMapping("/public/{lang}")
    public ResponseEntity<?> getList(@PathVariable("lang") LangEnum lang){
        return ResponseEntity.ok(regionService.getList(lang));
    }

    @GetMapping("/adm/get/{key}")
    public ResponseEntity<?> get(@PathVariable("key") String key,
                                 HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.getByKey(key));
    }


    @GetMapping("/adm/getById/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id,
                                 HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(regionService.getById(id));
    }
}
