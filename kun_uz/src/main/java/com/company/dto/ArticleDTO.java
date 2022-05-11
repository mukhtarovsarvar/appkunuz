package com.company.dto;

import com.company.enums.ArticleStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleDTO {
    private Integer id;
    @NotBlank(message = "title not valid")
    private String title;
    @NotBlank(message = "description not valid")
    private String description;
    @NotBlank(message = "content not valid")
    private String content;
    private Boolean visible;

    private Integer profileId;

    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    private ArticleStatus status;
    private LocalDateTime publishedDate;

    private String attachId;
    private Integer categoryId;
    private Integer regionId;
    private Integer typeId;

    private Integer viewCount;
    private Integer sharedCount;

    private List<Integer> tagIdList; // create
    private List<TagDTO> tagList; // get full

    private AttachDTO image;
    private LikeDTO like;
    private CategoryDto category;
    private RegionDto region;
    private ArticleTypeDto articleType;


}
