package com.daihui.Utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * url工具类
 * 470969043@qq.com
 *
 * @author daihui
 * @since 2017-01-04 1:40
 */
@Component
@Configuration
@PropertySource("classpath:book.properties")
public class URLTools {


    /**
     * 玄幻魔法
     */
    @Value("${xuanHuanMoFa}")
    private   String xuanHuanMoFa;
    /**
     * 武侠修真
     */
    @Value("${wuXiaXiuZhen}")
    private   String wuXiaXiuZhen;
    /**
     * 都市言情
     */
    @Value("${duShiYanQing}")
    private   String duShiYanQing;
    /**
     * 历史军事
     */
    @Value("${liShiJunShi}")
    private   String liShiJunShi;
    /**
     * 侦探推理
     */
    @Value("${zhenTanTuiLi}")
    private   String zhenTanTuiLi;
    /**
     * 网游动漫
     */
    @Value("${wangYouDongMan}")
    private   String wangYouDongMan;
    /**
     * 科幻小说
     */
    @Value("${keHuanXiaoShuo}")
    private   String keHuanXiaoShuo;
    /**
     * 恐怖灵异
     */
    @Value("${kongBuLingYi}")
    private   String kongBuLingYi;
    /**
     * 散文诗词
     */
    @Value("${sanWenShiCi}")
    private   String sanWenShiCi;
    /**
     * 其他
     */
    @Value("${qiTa}")
    private   String qiTa;
    /**
     * 小说root地址
     */
    @Value("${bookBaseURL}")
    private String bookBaseURL;

    /**
     * Description: 获取小说类别URL
     * @param bookClass
     * @Author daihui
     * @Date 2017-01-04
     */
    public  String getBookClassURL(int bookClass){
        String url=null;
        switch (bookClass){
            case 1:url = xuanHuanMoFa;break;
            case 2:url = wuXiaXiuZhen;break;
            case 3:url = duShiYanQing;break;
            case 4:url = liShiJunShi;break;
            case 5:url = zhenTanTuiLi;break;
            case 6:url = wangYouDongMan;break;
            case 7:url = keHuanXiaoShuo;break;
            case 8:url = kongBuLingYi;break;
            case 9:url = sanWenShiCi;break;
            case 10:url = qiTa;break;
        }
        return url;
    }


    /**
     * Description: 获取小说目录
     * @param bookCatelog
     * @Author daihui
     * @Date 2017-01-04
     */
    public String getBookCatelogURL(String bookCatelog){
        return bookBaseURL+bookCatelog;
    }

    /**
     * Description: 获取小说正文
     * @param bookCatelog
     * @Author daihui
     * @Date 2017-01-04
     */
    public String getContentURL(String bookCatelog){
        return bookBaseURL+bookCatelog+".html";
    }

    /**
     * 获取小伙类别名称
     * @param bookClass
     * @return
     */
    public String getBookClassName(int bookClass) {
        String name=null;
        switch (bookClass){
            case 1:name = "玄幻魔法";break;
            case 2:name = "武侠修真";break;
            case 3:name = "都市言情";break;
            case 4:name = "历史军事";break;
            case 5:name = "侦探推理";break;
            case 6:name = "网游动漫";break;
            case 7:name = "科幻小说";break;
            case 8:name = "恐怖灵异";break;
            case 9:name = "散文诗词";break;
            case 10:name = "其他";break;
        }
        return name;
    }


}
