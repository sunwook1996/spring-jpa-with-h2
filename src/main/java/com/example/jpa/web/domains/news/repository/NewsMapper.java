package com.example.jpa.web.domains.news.repository;


import com.example.jpa.web.domains.news.vo.NewsVo;
import org.hibernate.id.UUIDGenerator;

public class NewsMapper {

    public static News toEntity(NewsVo vo) {

        News result = new News();

        result.setSubject(vo.getSubject().substring(0, vo.getSubject().length()>50? 50 : vo.getSubject().length()));
        result.setContents(vo.getContents().substring(0, vo.getContents().length()>50? 50 : vo.getContents().length()));
        result.setSupplier(vo.getSupplier().substring(0, vo.getSupplier().length()>50? 50 : vo.getSupplier().length()));
        result.setDateStr(vo.getDateStr().substring(0, vo.getDateStr().length()>50? 50 : vo.getDateStr().length()));

        return result;
    }

    public static NewsVo toVo(News entity) {
        return null;
    }

}
