package com.company.service;

import com.company.dto.ComentDto;
import com.company.entity.ComentaryEntity;
import com.company.exception.AppBadRequestException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ArticleRepository;
import com.company.repository.ComentRepository;
import com.company.validation.ComentValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentService {
    private final ComentRepository comentRepository;
    private final ArticleRepository articleRepository;

    public ComentDto create(ComentDto dto, Integer pId) {
        ComentValidation.isValid(dto);
        articleRepository.findById(dto.getArticleId()).orElseThrow(() -> {
            throw new ItemNotFoundException("Item not found");
        });
        ComentaryEntity entity = new ComentaryEntity();
        entity.setProfileId(pId);
        entity.setArticleId(dto.getArticleId());
        entity.setContent(dto.getContent());
        comentRepository.save(entity);
        return toDtoSimple(entity);
    }

    public Boolean delete(Integer id){
        comentRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("Item not found");
        });
        comentRepository.deleteById(id);
        return true;
    }

    public PageImpl<ComentDto> getList(int page,int size){

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<ComentaryEntity> pageble = comentRepository.findAll(pageRequest);
        List<ComentDto> dtoList = pageble.stream().map(this::toDtoSimple).toList();
        return new PageImpl<>(dtoList,pageRequest,pageble.getTotalElements());
    }

    public ComentDto update(Integer comentId,ComentDto dto,Integer pId){
        ComentaryEntity entity = comentRepository.findById(comentId).orElseThrow(() -> {
            throw new ItemNotFoundException("Item not found");
        });
        if(!entity.getProfileId().equals(pId)){
            throw new AppBadRequestException("Not acces");
        }
        entity.setContent(dto.getContent());
        comentRepository.save(entity);
        return toDtoSimple(entity);
    }

    public ComentDto getById(Integer id){

        return toDtoSimple( comentRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("Item not found");
        }));
    }



    public ComentDto toDtoSimple(ComentaryEntity entity){
        ComentDto dto = new ComentDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setProfileId(entity.getProfileId());
        dto.setArticleId(entity.getArticleId());
        dto.setCreatedDate(entity.getCreateDate());
        return dto;
     }
}
