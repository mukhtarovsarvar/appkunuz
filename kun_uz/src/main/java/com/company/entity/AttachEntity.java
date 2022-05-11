package com.company.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Slf4j
@Setter
@Getter
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    private String id; // uuid
    @Column
    private String path;
    @Column
    private String extension;
    @Column(name = "origen_name")
    private String origenName;
    @Column()
    private Long size;
    @Column
    private LocalDateTime createdDate = LocalDateTime.now();

}
