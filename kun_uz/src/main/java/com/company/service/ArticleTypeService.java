package com.company.service;

import com.company.dto.ArticleTypeDto;
import com.company.entity.ArticleTypeEntity;
import com.company.enums.LangEnum;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ArticleTypeRepository;
import com.company.validation.ArticleTypeValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArticleTypeService {
    private final ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDto create(Integer pId,ArticleTypeDto dto){
        ArticleTypeValidation.isValid(dto);

        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setKey(dto.getKey());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setProfileId(pId);
        articleTypeRepository.save(entity);
        return toDtoSimple(entity);
    }

    public ArticleTypeDto toDtoSimple(ArticleTypeEntity entity){
        ArticleTypeDto dto = new ArticleTypeDto();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setKey(entity.getKey());
        dto.setNameEn(entity.getNameEn());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        return dto;
    }

    public ArticleTypeDto toDtoSimple(ArticleTypeEntity entity,LangEnum langEnum){
        ArticleTypeDto dto = new ArticleTypeDto();
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
        articleTypeRepository.findByKey(key).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        });
        articleTypeRepository.deleteByKey(key);
        return true;
    }

    public List<ArticleTypeDto> getList(){
       return  articleTypeRepository.findAll().stream().map(this::toDtoSimple).toList();
    }

    public List<ArticleTypeDto> getList(LangEnum lang){
   return   articleTypeRepository.findAll().stream().map(entity -> toDtoSimple(entity,lang)).toList();
    }

    public List<ArticleTypeDto> getList(int page,int size){
        return articleTypeRepository.findAll(PageRequest.of(page, size)).
                stream().map(this::toDtoSimple).toList();
    }

    public ArticleTypeDto update(String key,ArticleTypeDto dto){
        ArticleTypeEntity entity = articleTypeRepository.findByKey(key).orElseThrow(() -> {
            throw new ItemNotFoundException("item not found");
        });

        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        articleTypeRepository.save(entity);

        return toDtoSimple(entity);
    }

    public ArticleTypeDto getByKey(String key){
        return toDtoSimple(articleTypeRepository.findByKey(key).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        }));
    }
    public ArticleTypeDto getById(Integer id, LangEnum lang) {
        Optional<ArticleTypeEntity> optional = articleTypeRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Id Not Found");
        }
        ArticleTypeEntity article = optional.get();
        return toDTO(article, lang);
    }
    private ArticleTypeDto toDTO(ArticleTypeEntity entity, LangEnum lang) {
        ArticleTypeDto dto = new ArticleTypeDto();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        switch (lang) {
            case uz:
                dto.setName(entity.getNameUz());
                break;
            case ru:
                dto.setName(entity.getNameRu());
                break;
            case en:
                dto.setName(entity.getNameEn());
                break;
        }
        return dto;
    }

    public ArticleTypeDto getById (Integer id){
        return toDtoSimple(articleTypeRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        }));
    }

}
