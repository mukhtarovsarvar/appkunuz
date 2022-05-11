package com.company.service;

import com.company.dto.CategoryDto;
import com.company.dto.RegionDto;
import com.company.entity.CategoryEntity;
import com.company.entity.RegionEntity;
import com.company.enums.LangEnum;
import com.company.exception.ItemNotFoundException;
import com.company.repository.RegionRepository;
import com.company.validation.RegionValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    public RegionDto getById(Integer id, LangEnum lang) {
        RegionEntity regionEntity = regionRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("Region not found");
        });

        return toDTO(regionEntity, lang);
    }
    private RegionDto toDTO(RegionEntity entity, LangEnum lang) {
        RegionDto dto = new RegionDto();
        dto.setId(entity.getId());
        dto.setKey(entity.getKey());
        switch (lang) {
            case uz -> dto.setName(entity.getNameUz());
            case ru -> dto.setName(entity.getNameRu());
            case en -> dto.setName(entity.getNameEn());
        }
        return dto;
    }

    public RegionDto create(Integer pId, RegionDto dto){
        RegionValidation.isValid(dto);

        RegionEntity entity = new RegionEntity();
        entity.setNameUz(dto.getNameUz());
        entity.setKey(dto.getKey());
        entity.setNameRu(dto.getNameRu());
        entity.setNameEn(dto.getNameEn());
        entity.setProfileId(pId);
        regionRepository.save(entity);
        return toDtoSimple(entity);
    }

    public RegionDto toDtoSimple(RegionEntity entity){
        RegionDto dto = new RegionDto();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setKey(entity.getKey());
        dto.setNameEn(entity.getNameEn());
        dto.setNameUz(entity.getNameUz());
        dto.setNameRu(entity.getNameRu());
        return dto;
    }

    public RegionDto toDtoSimple(RegionEntity entity, LangEnum langEnum){
        RegionDto dto = new RegionDto();
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
        regionRepository.findByKey(key).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        });
        regionRepository.deleteByKey(key);
        return true;
    }

    public List<RegionDto> getList(){
        return  regionRepository.findAll().stream().map(this::toDtoSimple).toList();
    }

    public List<RegionDto> getList(LangEnum lang){
        return regionRepository.findAll().stream().map(regionEntity -> toDtoSimple(regionEntity, lang)).toList();
    }

    public List<RegionDto> getList(int page,int size){
        return regionRepository.findAll(PageRequest.of(page, size)).
                stream().map(this::toDtoSimple).toList();
    }

    public RegionDto update(String key, RegionDto dto){
        RegionEntity entity = regionRepository.findByKey(key).orElseThrow(() -> {
            throw new ItemNotFoundException("item not found");
        });

        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        regionRepository.save(entity);

        return toDtoSimple(entity);
    }

    public RegionDto getById (Integer id){
        return toDtoSimple(regionRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        }));
    }
    public RegionDto getByKey(String key){
        return toDtoSimple(regionRepository.findByKey(key).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        }));
    }

}
