package com.company.service;

import com.company.dto.AuthDto;
import com.company.dto.ProfileDto;
import com.company.dto.RegstrationDto;
import com.company.entity.ProfileEntity;
import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import com.company.exception.AppForbiddenException;
import com.company.exception.PasswordOrEmailWrongException;
import com.company.repository.ProfileRepository;
import com.company.util.JwtUtil;
import com.company.validation.AuthValidation;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final ProfileService profileService;
    private final ProfileRepository profileRepository;
    private final EmailService emailService;


    public ProfileDto auth(AuthDto dto) {
        ProfileDto email = profileService.getByEmail(dto.getEmail());
        String pswd = DigestUtils.md5Hex(dto.getPassword());
        if (!email.getPassword().equals(pswd)) {
            throw new PasswordOrEmailWrongException("password wrong");
        }
        if (!email.getStatus().equals(ProfileStatus.ACTIVE)) {
            throw new AppForbiddenException("status not active");
        }
        email.setJwt(JwtUtil.encode(email.getId(), email.getRole()));


        return email;
    }

    public void reg(RegstrationDto dto) {
        AuthValidation.isValid(dto);

        ProfileEntity profile = new ProfileEntity();
        profile.setSurname(dto.getSurname());
        profile.setStatus(ProfileStatus.NO_ACTIVE);
        profile.setRole(ProfileRole.USER);
        profile.setPassword(dto.getPassword());
        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profileRepository.save(profile);

        Thread thread = new Thread() {
            @Override
            public void run() {
                sendVerificationEmail(profile);
            }
        };
        thread.start();
    }

    private void sendVerificationEmail(ProfileEntity entity) {
        StringBuilder builder = new StringBuilder();
        String jwt = JwtUtil.encode(entity.getId());
        builder.append("Salom bormsin \n");
        builder.append("To verify your registration click to next link.");
        builder.append("http://localhost:8080/auth/verification/").append(jwt);
        builder.append("\nMazgi!");
        emailService.send(entity.getEmail(), "Activate Your Registration", builder.toString());
    }

    public void verification(String jwt) {
        Integer userId = null;
        try {
            userId = JwtUtil.decodeAndGetId(jwt);
        } catch (JwtException e) {
            e.printStackTrace();
        }
        profileRepository.changeStatus(userId,ProfileStatus.ACTIVE);
    }
}
