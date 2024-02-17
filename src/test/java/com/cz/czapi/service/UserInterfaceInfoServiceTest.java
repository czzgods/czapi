package com.cz.czapi.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@SpringBootTest
public class UserInterfaceInfoServiceTest {
    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Resource
    private SentenceService sentenceService;

    @Test
    public void invokeCount() {
        boolean b = userInterfaceInfoService.invokeCount(1l, 1l);
        Assertions.assertTrue(b);
    }
    @Test
    public void sentence(){
        LambdaQueryWrapper<Sentence> queryWrapper = new LambdaQueryWrapper<>();
        List<Sentence> list = sentenceService.list(queryWrapper);
        System.out.println(list);
    }

    /**
     * 多线程批量插入数据库
     */
    private ExecutorService executor = new ThreadPoolExecutor(
            60,
            1000,
            10000, TimeUnit.MICROSECONDS,
            new ArrayBlockingQueue<>(1000));
    @Test
    public void getSentence(){
        String url = "http://mingyan123.com/";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        }catch (IOException e){
            throw new RuntimeException(e.getMessage());
        }
        Elements elements = doc.select("mingyan123-ju");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        int j = 0;
        List<CompletableFuture> futureList = new ArrayList<>();
        for (Element element : elements) {
            String sentence = element.text();
            for (int i = 0; i < 10; i++) {
                List<Sentence> sentenceList = new ArrayList<>();
                while (true) {
                    j++;
                    Sentence s = new Sentence();
                    s.setSentence(sentence);
                    sentenceList.add(s);
                    if(j % 1000 == 0){
                        break;
                    }
                }
                CompletableFuture<Void>future = CompletableFuture.runAsync(()->{
                    sentenceService.saveBatch(sentenceList,10000);
                },executor);
                futureList.add(future);
            }
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
        }
        stopWatch.stop();
        if (stopWatch.isRunning()) {
            System.out.println(stopWatch.getLastTaskTimeMillis());
        } else {
            System.out.println("StopWatch is not running.");
        }
    }

    @Test
    public void getSentenceDan() {
        String url = "http://mingyan123.com/";
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        Elements elements = doc.select("mingyan123-ju");
        List<Sentence> sentenceList = new ArrayList<>();
        for (Element element : elements) {
            String sentence = element.text();
            Sentence s = new Sentence();
            s.setSentence(sentence);
            sentenceList.add(s);
        }
        boolean batch = sentenceService.saveBatch(sentenceList);
        if(batch){
            System.out.println("插入成功!");
        }else {
            System.out.println("插入失败!");
        }
    }
}