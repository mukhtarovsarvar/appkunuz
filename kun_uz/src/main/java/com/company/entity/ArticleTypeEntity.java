package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter

public class ArticleTypeEntity extends BasicEntity {
    @Column(nullable = false,unique = true)
    private String nameUz;
    @Column(nullable = false,unique = true)
    private String nameEn;
    @Column(nullable = false,unique = true)
    private String nameRu;
    @Column(nullable = false,unique = true)
    private String key;

    @Column(name = "profile_id",nullable = false)
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;

}
