package com.daihui.Controller;

import com.daihui.Model.Video;
import com.daihui.Spider.VideoSpider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 470969043@qq.com
 * 视频播放
 * @author daihui
 * @since 2017/3/9.
 */
@Controller
@RequestMapping("video")
@PropertySource("classpath:video.properties")
public class VideoController {

    @Value("${videoUrl1}")
    private String videoUrl1;

    @Value("${videoUrl2}")
    private String videoUrl2;

    @Resource
    private VideoSpider videoSpider;

/**
 * 登陆休息室
 * @param username xiuxishi
 * @param password woyaokanpian
 * @return
 */
@RequestMapping(value = "login",method = RequestMethod.POST)
public String login(Model model, String username, String password){
    String view = "index";
    if(null != username && !"".equals(username) && !"null".equals(username)){
        if(null != password && !"".equals(password) && !"null".equals(password)){
            if(username.equals("xiuxishi")&&password.equals("woyaokanpian")){
                List<Video> videoDateList = videoSpider.getRoots(videoUrl2);
                model.addAttribute("videoDateList",videoDateList);
                view =  "video/index";
            }
        }
    }
    return view;
}


    /**
     * 影片目录
     * @param date 日期格式的目录名称
     * @return
     */
    @RequestMapping(value = "catelog/{date}",method = RequestMethod.GET)
    public String catelog(Model model, @PathVariable(value = "date") String date){
        String view = "video/catelog";
        String url = videoUrl2+date;
        List<Video> videoCatelogList = videoSpider.getVideoCatelogs(url);
        model.addAttribute("videoCatelogList",videoCatelogList);
        model.addAttribute("date",date);
        return view;
    }


    /**
     * 进入某部视频页面
     * @param model
     * @param date
     * @param videoName
     * @return
     */
    @RequestMapping(value = "catelog/{date}/{videoName}",method = RequestMethod.GET)
    public String videos(Model model, @PathVariable(value = "date") String date,@PathVariable(value = "videoName") String videoName){
        String view = "video/video";
        String url = videoUrl2+date+"/"+videoName;
        String videoUrl = url + "/1/hls/index.m3u8";
        List<Video> videoImgList = videoSpider.getVideo(url);
        model.addAttribute("videoImgList",videoImgList);
        model.addAttribute("initImgUrl",videoImgList.get(0).getUrl());
        model.addAttribute("videoUrl",videoUrl);
        return view;
    }



//    @ResponseBody
//    @RequestMapping(value = "play/{date}/{videoName}",method = RequestMethod.GET)
//    public String playVideo(Model model, @PathVariable(value = "date") String date,@PathVariable(value = "videoName") String videoName){
////        String view = "video/video";
//        String url = videoUrl2+date+"/"+videoName;
//        List<Video> videoCatelogList = videoSpider.getVideoCatelogs(url);
//        model.addAttribute("videoCatelogList",videoCatelogList);
//        return view;
//    }








}



















