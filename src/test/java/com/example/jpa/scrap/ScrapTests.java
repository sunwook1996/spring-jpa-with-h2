package com.example.jpa.scrap;

import com.example.jpa.web.domains.news.repository.News;
import com.example.jpa.web.domains.news.repository.NewsMapper;
import com.example.jpa.web.domains.news.repository.NewsRepository;
import com.example.jpa.web.domains.news.vo.NewsVo;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import org.aspectj.lang.annotation.Before;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ScrapTests {

    @LocalServerPort
    private int testPortInformation;

    private static ObjectMapper jacksonMapper = null;

    private static String target_url = "https://land.naver.com/news/newsRead.naver?type=headline&prsco_id=366&arti_id=0000789419";

    @Autowired
    private NewsRepository repository;

    @BeforeAll
    void mapper_setting() {
        jacksonMapper = new ObjectMapper();
    }


    @Test
    public void naver_1() throws Exception {

        System.out.println(testPortInformation);

        Document document = Jsoup.connect(target_url).get();

        NewsVo newsVo = getNews(document);

        News news = NewsMapper.toEntity(newsVo);
        repository.save(news);

        List<News> all = repository.findAll();

        List<News> filter = all.stream().filter(it -> it.getSubject().contains("아무도")).collect(Collectors.toList());

        filter.forEach(System.out::println);

//        String json = jacksonMapper.writeValueAsString(newsVo);
//
//        System.out.println(json);

    }

    @Test
    public void legacy_1() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("key", "TEST_KEY");
        map.put("value", "TEST_VALUE");

        TestObject testObject = jacksonMapper.convertValue(map, TestObject.class);

        System.out.println(testObject.getKey());
        System.out.println(testObject.getValue());

    }

    private NewsVo getNews(Document document) {

        return NewsVo.builder()
                .subject(document.select(".article_header h3").text())
                .contents(document.select("#articleBody").text())
                .supplier(document.select("#news_writer").text())
                .dateStr(document.select("#news_write_date").text())
                .build();
    }




}
