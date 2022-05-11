package com.company.controller;


import com.company.dto.ArticleTypeDto;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.service.ArticleTypeService;
import com.company.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/articleType")
@RequiredArgsConstructor
@Slf4j
public class ArticleTypeController {
    private final ArticleTypeService articleTypeService;

    @PostMapping ("/adm")
    public ResponseEntity<?> create(@RequestBody @Valid ArticleTypeDto dto,
                                    HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("create articleType from pId  "+pId+ " type : {}",dto);
        return ResponseEntity.ok(articleTypeService.create(pId, dto));
    }

    @DeleteMapping("/adm/{key}")
    public ResponseEntity<?> deleteByKey(@PathVariable("key") String key,
                                         HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("delete articleType from pId  "+pId+ " type key : {}",key);
        return ResponseEntity.ok(articleTypeService.delete(key));
    }

    @PutMapping("/adm/update/{key}")
    public ResponseEntity<?> updateByKey(@PathVariable("key") String key,
                                         @RequestBody @Valid ArticleTypeDto dto,
                                         HttpServletRequest request){
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update articleType from pId  "+pId+ " type : {}",dto);
         return ResponseEntity.ok(articleTypeService.update(key, dto));
    }
    @GetMapping("/adm/list")
    public ResponseEntity<?> getList(HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(articleTypeService.getList());
    }

    @GetMapping("/public/{lang}")
    public ResponseEntity<?> getList(@PathVariable("lang")LangEnum lang){
        return ResponseEntity.ok(articleTypeService.getList(lang));
    }

    @GetMapping("/adm/get/{key}")
    public ResponseEntity<?> get(@PathVariable("key") String key,
                                 HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(articleTypeService.getByKey(key));
    }


    @GetMapping("/adm/getById/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id,
                                 HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(articleTypeService.getById(id));
    }
}
