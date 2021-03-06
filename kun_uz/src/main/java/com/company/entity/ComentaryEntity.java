package com.company.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class ComentaryEntity extends BasicEntity{

    @Column(columnDefinition = "text")
    private String content;

    @Column(name ="profile_id",nullable = false)
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;

    @Column(name ="article_id",nullable = false)
    private Integer articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="article_id",insertable = false,updatable = false)
    private ArticleEntity article;
}
