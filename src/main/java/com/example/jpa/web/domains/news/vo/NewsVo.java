package com.example.jpa.web.domains.news.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NewsVo {

    private String subject;
    private String contents;
    private String supplier;
    private String dateStr;
    private String addOns;

}
