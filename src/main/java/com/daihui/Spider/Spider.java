package com.daihui.Spider;

import com.daihui.Model.Book;
import com.daihui.Model.Catelog;
import com.daihui.Utils.URLTools;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 爬虫
 * 470969043@qq.com
 *
 * @author daihui
 * @since 2017-01-03 22:47
 */
@Configuration
@PropertySource("classpath:book.properties")
@Service
public class Spider {

    @Value("${bookBaseURL}")
    private String bookBaseURL;
    @Resource
    private URLTools urlTools;
    //获取小说书名列表
    //传入参数为小说列表页URL
    //0,未完结。1，已完结。-1，已完结，且已经最后检查章节
    public List<Book> getBook(String Url) {
        List<Book> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(Url).timeout(30000).get();
            Elements link = doc.select("div.list ul li a");
            int num = link.size();
            for (int i = 1; i < num; i = i + 2) {
                String BookName = link.get(i).text();
                String BookUrl = link.get(i).attr("href").split(bookBaseURL)[1];
                String BookClass = link.get(i).className();

                Book book = new Book();
                book.setName(BookName);
                book.setUrl(BookUrl);
                if(BookClass.equals("state")){
                    book.setState(1);
                }else{
                    book.setState(0);
                }
                result.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    //获取一本书的目录，并把没有的章节添加进去
    @Async
    public  List<Catelog>  getCatelog(String bookUrl){
        List<Catelog> catelogList = new ArrayList<>();
        String url = urlTools.getBookCatelogURL(bookUrl);

        try{
            Document doc = Jsoup.connect(url).timeout(50000).get();
            Elements link = doc.select("table#at a");

            int num = link.size();
            for(int i=0;i<num;i++){
                String temp_name = link.get(i).text();
                String temp_link = link.get(i).attr("href");

                Catelog catelog = new Catelog();
                catelog.setState(0);
                catelog.setName(temp_name);
                catelog.setUrl(bookUrl+temp_link);
                catelog.setOrder(i);
                catelogList.add(catelog);
            }
        }catch (Exception e){
//            e.printStackTrace();
            System.out.println("获取目录超时："+bookUrl);
        }
        return catelogList;
    }

    //获取章节正文
    //@Async
    public Map<String,String> getContent(String catelogUrl){
        Map<String,String> result = new HashMap<>();

        String url = urlTools.getContentURL(catelogUrl);
        try{
            Document doc = Jsoup.connect(url).timeout(10000).get();
            Elements link = doc.select("#contents");//正文
            Elements chapters = doc.select("#amain dl dd");//章节名称
            Elements pages = doc.select("#footlink a");//上一页，目录，下一页

            String content = link.get(0).html();
            String chapter = chapters.get(0).text();
            String lastPage = pages.get(0).attr("href").split("/html")[1];
            String catelog = pages.get(1).attr("href").split("/html")[1];
            String nextPage = pages.get(2).attr("href").split("/html")[1];

            result.put("content",content);
            result.put("chapter",chapter);
            result.put("lastPage",lastPage);
            result.put("catelog",catelog);
            result.put("nextPage",nextPage);
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

}
