package com.company.controller;

import com.company.dto.ComentDto;
import com.company.enums.ProfileRole;
import com.company.service.ComentService;
import com.company.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/coment")
@Slf4j
public class ComentController {

    private final ComentService comentService;

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody @Valid ComentDto dto,
                                    HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request);
        log.info("create comment from pId  "+pId+ " comment : {}",dto);
        return ResponseEntity.ok(comentService.create(dto, pId));
    }

    @GetMapping("/adm/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(comentService.getById(id));
    }

    @GetMapping("")
    public ResponseEntity<?> getList(@RequestParam(value = "page", defaultValue = "0") int page,
                                     @RequestParam(value = "size", defaultValue = "3") int size) {
        return ResponseEntity.ok(comentService.getList(page, size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(comentService.delete(id));
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<?> update(@PathVariable("commentId") Integer commentId,
                                    @RequestBody @Valid ComentDto dto,
                                    HttpServletRequest request){
        Integer pId = JwtUtil.getIdFromHeader(request);
        return ResponseEntity.ok(comentService.update(commentId,dto,pId));
    }


}
