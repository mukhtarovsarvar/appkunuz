package com.company.controller;

import com.company.dto.ProfileDto;
import com.company.enums.ProfileRole;
import com.company.service.ProfileService;
import com.company.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    // httprequest tekshirish kk
    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody @Valid ProfileDto dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.create(dto));
    }


    //httprequest tekshirish kk
    @GetMapping("/adm/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getById(id));
    }

    @GetMapping("/adm/get")
    public ResponseEntity<?> getByEmail(@RequestBody ProfileDto dto,
                                     HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getByEmail(dto.getEmail()));
    }

    @GetMapping("/adm")
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "5") int size,
                                     HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getList(page, size));
    }

    @DeleteMapping("/adm")
    public ResponseEntity<?> delete(@RequestBody ProfileDto dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.delete(dto.getEmail()));
    }

    @DeleteMapping("/adm/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.delete(id));
    }

    @PutMapping("/adm/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id,
                                    @RequestBody @Valid ProfileDto dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.update(id,dto));
    }

    @PutMapping("/adm")
    public ResponseEntity<?> update(@RequestBody ProfileDto dto,
                                    HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(profileService.update(dto));
    }


}
