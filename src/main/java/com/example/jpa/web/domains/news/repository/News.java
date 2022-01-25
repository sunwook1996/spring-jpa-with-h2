package com.example.jpa.web.domains.news.repository;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;

@Entity
@Data
public class News {

    @Id
    @GeneratedValue
    private Integer genId;

    @Column
    @Nullable
    private String subject;

    @Column
    @Nullable
    private String contents;

    @Column
    @Nullable
    private String supplier;

    @Column
    @Nullable
    private String dateStr;

    @Column
    @Nullable
    private String addOns;

}
