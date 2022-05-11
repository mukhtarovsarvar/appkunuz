package com.company.controller;


import com.company.dto.ArticleTypeDto;
import com.company.dto.CategoryDto;
import com.company.entity.CategoryEntity;
import com.company.enums.LangEnum;
import com.company.enums.ProfileRole;
import com.company.exception.ItemNotFoundException;
import com.company.service.ArticleTypeService;
import com.company.service.CategoryService;
import com.company.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("/adm")
    public ResponseEntity<?> create(@RequestBody @Valid CategoryDto dto,
                                    HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("create category from pId  "+pId+ " category : {}",dto);
        return ResponseEntity.ok(categoryService.create(pId, dto));
    }

    @DeleteMapping("/adm/{key}")
    public ResponseEntity<?> deleteByKey(@PathVariable("key") String key,
                                         HttpServletRequest request) {
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("delete category from pId  "+pId+ " category  id: {}",key);

        return ResponseEntity.ok(categoryService.delete(key));
    }

    @PutMapping("/adm/update/{key}")
    public ResponseEntity<?> updateByKey(@PathVariable("key") String key,
                                         @RequestBody @Valid CategoryDto dto,
                                         HttpServletRequest request){
        Integer pId = JwtUtil.getIdFromHeader(request, ProfileRole.ADMIN);
        log.info("update category from pId  "+pId+ " category : {}",dto);
        return ResponseEntity.ok(categoryService.update(key, dto));
    }
    @GetMapping("/adm/list")
    public ResponseEntity<?> getList(HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(categoryService.getList());
    }

    @GetMapping("/public/{lang}")
    public ResponseEntity<?> getList(@PathVariable("lang") LangEnum lang){
        return ResponseEntity.ok(categoryService.getList(lang));
    }

    @GetMapping("/adm/get/{key}")
    public ResponseEntity<?> get(@PathVariable("key") String key,
                                 HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(categoryService.getByKey(key));
    }


    @GetMapping("/adm/getById/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Integer id,
                                 HttpServletRequest request){
        JwtUtil.getIdFromHeader(request,ProfileRole.ADMIN);
        return ResponseEntity.ok(categoryService.getById(id));
    }

}
