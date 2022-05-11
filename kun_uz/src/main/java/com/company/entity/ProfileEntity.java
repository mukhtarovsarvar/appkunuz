package com.company.entity;

import com.company.enums.ProfileRole;
import com.company.enums.ProfileStatus;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Table(name = "profile")
@Getter
@Setter
public class ProfileEntity extends BasicEntity {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String surname;
    @Column(nullable = false,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProfileRole role;
    @Column
    private Boolean visible = true;
    @Column
    @Enumerated(EnumType.STRING)
    private ProfileStatus status;



}
