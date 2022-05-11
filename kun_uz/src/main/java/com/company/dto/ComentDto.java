package com.company.dto;

import com.company.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
public class ComentDto {
    private Integer id;
    @NotBlank(message = "content not valid")
    private String content;
    private Integer profileId;
    private Integer articleId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
