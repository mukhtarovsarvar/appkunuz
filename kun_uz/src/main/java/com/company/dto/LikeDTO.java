package com.company.dto;

import com.company.enums.LikeStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LikeDTO extends BasicDto{
    private LikeStatus status;
    private Integer profileId;
    private Integer articleId;


    private Integer likeCount;
    private Integer disLikeCount;
}
