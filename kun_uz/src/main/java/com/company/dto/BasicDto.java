package com.company.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BasicDto {
    private Integer id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
