package com.daihui.Controller;

import com.daihui.Model.Book;
import com.daihui.Model.Catelog;
import com.daihui.Spider.Spider;
import com.daihui.Utils.URLTools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 470969043@qq.com
 *
 * @author daihui
 * @since 2017-01-03 22:20
 */
@Controller
@Configuration
@PropertySource("classpath:book.properties")
public class IndexController {

    @Value("${xuanHuanMoFa}")
    private String xuanHuanMoFa;

    @Resource
    private Spider spider;
    @Resource
    private URLTools urlTools;

    /**
     * 进入首页
     * @param model
     * @return
     */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String index(Model model) {
        String url = urlTools.getBookClassURL(1);
        String bookClassName =urlTools.getBookClassName(1);
        List<Book> bookList = spider.getBook(url);

        model.addAttribute("bookList",bookList);
        model.addAttribute("bookClassName",bookClassName);
        return "index";
    }

    /**
     * 获取不同类型小说
     * @param bookClass
     * @param model
     * @return
     */
    @RequestMapping(value = "/bookClass/{bookClass}",method = RequestMethod.GET)
    public String book(@PathVariable(value = "bookClass") int bookClass, Model model){
        String url = urlTools.getBookClassURL(bookClass);
        List<Book> bookList = spider.getBook(url);
        String bookClassName =urlTools.getBookClassName(bookClass);
        model.addAttribute("bookList",bookList);
        model.addAttribute("bookClassName",bookClassName);
        return "index";
    }

    /**
     * Description: 获取每本书的目录
     * @param catelog_1
     * @param catelog_2
     * @param bookName
     * @param model
     * @Author daihui
     * @Date 2017-01-04
     */
    @RequestMapping(value = "/Book/{bookName}/{catelog_1}/{catelog_2}",method = RequestMethod.GET)
    public String getCatalog(@PathVariable(value = "bookName") String bookName,
                             @PathVariable(value = "catelog_1") String catelog_1,
                             @PathVariable(value = "catelog_2") String catelog_2,
                             Model model){
        Book book = new Book();
        book.setName(bookName);

        List<Catelog> catelogList = spider.getCatelog("/"+catelog_1+"/"+catelog_2+"/");
        model.addAttribute("catelogList",catelogList);
        model.addAttribute("book",book);
        return "catelog";
    }




    /**
     * Description: 获取正文内容
     * @param bookName
     * @param catelog_1
     * @param catelog_2
     * @param content_id
     * @param model
     * @Author daihui
     * @Date 2017-01-04 
     * @return 
     */
    @RequestMapping(value = "/Read/{bookName}/{catelog_1}/{catelog_2}/{content_id}",method = RequestMethod.GET)
    public String read( @PathVariable(value = "bookName") String bookName,
                        @PathVariable(value = "catelog_1") String catelog_1,
                        @PathVariable(value = "catelog_2") String catelog_2,
                        @PathVariable(value = "content_id") int content_id,Model model){

        Book book = new Book();
        book.setName(bookName);

        Map<String,String> result = spider.getContent("/"+catelog_1+"/"+catelog_2+"/"+content_id);

        model.addAttribute("book",book);
        model.addAttribute("content",result.get("content"));
        model.addAttribute("chapter",result.get("chapter"));
        model.addAttribute("lastPage",result.get("lastPage"));
        model.addAttribute("catelog",result.get("catelog"));
        model.addAttribute("nextPage",result.get("nextPage"));

        return "read";
    }

}
