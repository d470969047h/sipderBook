package com.daihui.Spider;

import com.daihui.Model.Book;
import com.daihui.Model.Video;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * 470969043@qq.com
 * 视频爬虫
 *
 * @author daihui
 * @since 2017/3/10.
 */
@Service
public class VideoSpider {


    public List<Video> getRoots(String videoUrl) {
        List<Video> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(videoUrl).timeout(30000).get();
            Elements link = doc.select("pre a");
            int num = link.size();
            for (int i = 0; i < num; i++) {
//                String videoDateUrl = link.get(i).attr("href");
                String videoDateName = link.get(i).text();
                int len = videoDateName.length();
                if (8 == len && !videoDateName.contains(".")) {
                    Video video = new Video();
                    video.setUrl(videoDateName);
                    video.setName(videoDateName);
                    result.add(video);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public List<Video> getVideoCatelogs(String url){
        List<Video> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).timeout(30000).get();
            Elements link = doc.select("pre a");
            int num = link.size();
            for (int i = 0; i < num; i++) {
//                String videoDateUrl = link.get(i).attr("href");
                String videoDateName = link.get(i).text();
                Video video = new Video();
                video.setUrl(videoDateName);
                video.setName(videoDateName);
                result.add(video);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Video> getVideo(String url){
        List<Video> result = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).timeout(30000).get();
            Elements link = doc.select("pre a");
            int num = link.size();
            for (int i = 0; i < num; i++) {
                String videoName = link.get(i).text();
                if(videoName.endsWith(".jpg")){
                    Video video = new Video();
                    video.setUrl(url+"/"+videoName);
                    video.setName(videoName);
                    result.add(video);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }










}
