package com.company.service;

import com.company.dto.ProfileDto;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileStatus;
import com.company.exception.ItemFoundException;
import com.company.exception.ItemNotFoundException;
import com.company.repository.ProfileRepository;
import com.company.validation.ProfileValidation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;
    public ProfileDto create(ProfileDto dto) {

        ProfileValidation.isValid(dto);

        Optional<ProfileEntity> byEmail = profileRepository.findByEmail(dto.getEmail());
        if(byEmail.isPresent()){
            throw new ItemFoundException("item found");
        }

        ProfileEntity entity = new ProfileEntity();
        String pswd = DigestUtils.md5Hex(dto.getPassword());

        entity.setPassword(pswd);
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setStatus(ProfileStatus.ACTIVE);
        entity.setSurname(dto.getSurname());
        entity.setRole(dto.getRole());

        profileRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setStatus(entity.getStatus());

        return dto;
    }
    public ProfileDto toDtoSimple(ProfileEntity entity){
        ProfileDto dto = new ProfileDto();
        dto.setId(entity.getId());
        dto.setCreateDate(entity.getCreateDate());
        dto.setPassword(entity.getPassword());
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setRole(entity.getRole());
        dto.setStatus(entity.getStatus());
        dto.setSurname(entity.getSurname());
        return dto;
    }


    public ProfileEntity get(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Not Found!"));
    }

    public ProfileDto getById(Integer id) {
        ProfileEntity entity = profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemFoundException("Item not found");
        });
        return toDtoSimple(entity);
    }

    public List<ProfileDto> getList(int page,int size) {
        Page<ProfileEntity> pageList= profileRepository.findAll(PageRequest.of(page, size));
        return pageList.stream().map(this::toDtoSimple).toList();
    }
    public Boolean delete(String email) {
        profileRepository.findByEmail(email).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        });
        profileRepository.deleteByEmail(email);
        return true;
    }
    public Boolean delete(Integer id) {
        profileRepository.findById(id).orElseThrow(()->{
            throw new ItemNotFoundException("item not found");
        });
        profileRepository.deleteById(id);
        return true;
    }
    public ProfileDto update(Integer id,ProfileDto dto) {
        ProfileEntity entity = profileRepository.findById(id).orElseThrow(() -> {
            throw new ItemNotFoundException("item not found");
        });
        entity.setName(dto.getName());
        entity.setRole(dto.getRole());
        entity.setPassword(DigestUtils.md5Hex(dto.getPassword()));
        profileRepository.save(entity);
        return toDtoSimple(entity);
    }
    public ProfileDto update(ProfileDto dto) {
        ProfileEntity entity = profileRepository.findByEmail(dto.getEmail()).orElseThrow(() -> {
            throw new ItemNotFoundException("item not found");
        });
        entity.setName(dto.getName());
        entity.setRole(dto.getRole());
        entity.setSurname(dto.getSurname());
        entity.setPassword(DigestUtils.md5Hex(dto.getPassword()));
        profileRepository.save(entity);
        return toDtoSimple(entity);
    }
    public ProfileDto getByEmail(String email) {
        ProfileEntity entity = profileRepository.findByEmail(email).orElseThrow(() -> {
            throw new ItemNotFoundException("item not found");
        });
        return toDtoSimple(entity);
    }
}
