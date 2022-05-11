package com.company.service;

import com.company.dto.ArticleTypeDto;
import com.company.dto.CategoryDto;
import com.company.entity.ArticleTypeEntity;
import com.company.entity.CategoryEntity;
import com.company.enums.LangEnum;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.CategoryRepository;
import com.company.validation.CategoryValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryDto create(Integer pId, CategoryDto dto){
        CategoryValidation.isValid(dto);

        CategoryEntity entity = new CategoryEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setKey(dto.getKey());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setProfileId(pId);
        categoryRepository.save(entity);
        return toDtoSimple(entity);
    }

    public CategoryDto toDtoSimple(CategoryEntity entity){
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setKey(entity.getKey());
        dto.setNameEn(entity.getNameEn());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        return dto;
    }

    public CategoryDto toDtoSimple(CategoryEntity entity, LangEnum langEnum){
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setKey(entity.getKey());
        switch (langEnum){
            case uz -> dto.setName(entity.getNameUz());
            case ru -> dto.setName(entity.getNameRu());
            case en -> dto.setName(entity.getNameEn());
        }
        return dto;
    }

    public Boolean delete(String key){
        categoryRepository.findByKey(key).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        });
        categoryRepository.deleteByKey(key);
        return true;
    }

    public List<CategoryDto> getList(){
        return  categoryRepository.findAll().stream().map(this::toDtoSimple).toList();
    }



    public List<CategoryDto> getList(LangEnum lang){
        return categoryRepository.findAll().stream().map(categoryEntity -> toDtoSimple(categoryEntity, lang)).toList();
    }

    public List<CategoryDto> getList(int page,int size){
        return categoryRepository.findAll(PageRequest.of(page, size)).
                stream().map(this::toDtoSimple).toList();
    }

    public CategoryDto update(String key,CategoryDto dto){
        CategoryEntity entity = categoryRepository.findByKey(key).orElseThrow(() -> {
            throw new ItemNotFoundException("item not found");
        });

        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        categoryRepository.save(entity);

        return toDtoSimple(entity);
    }

    public CategoryDto getById (Integer id){
        return toDtoSimple(categoryRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        }));
    }

    public CategoryDto getById(Integer id, LangEnum lang) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Id Not Found");
        }
        CategoryEntity category = optional.get();
        return toDTO(category, lang);
    }
    private CategoryDto toDTO(CategoryEntity entity, LangEnum lang) {
        CategoryDto dto = new CategoryDto();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        switch (lang) {
            case uz -> dto.setName(entity.getNameUz());
            case ru -> dto.setName(entity.getNameRu());
            case en -> dto.setName(entity.getNameEn());
        }

        return dto;
    }

    public CategoryDto getByKey(String key){
        return toDtoSimple(categoryRepository.findByKey(key).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        }));
    }

}
