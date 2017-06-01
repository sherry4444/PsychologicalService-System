package com.test.controller;

import com.test.commons.CreateTxtAndWrite;
import com.test.commons.Filefunction;
import com.test.commons.HttpInfo;
import com.test.domain.*;
import com.test.domain.Article;
import com.test.service.ArticleService;
import com.test.service.ArticleService;
import com.test.service.CommentService;
import jdk.nashorn.internal.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/31.
 */
@Controller
public class ArticleController{

    private Logger logger = LoggerFactory.getLogger(ArticleController.class);

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    Filefunction filefunction = new Filefunction();

    private int totalNumber;

    @RequestMapping(value = "/viewComment/{id}",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Map<String,Object> addArticle(@PathVariable("id")Integer id) {
        Comment comment = new Comment();
        comment.setArticleId(id);
        Map<String,Object> map = new HashMap<String, Object>();
        List<Comment> list = commentService.findCommentAll(comment);
        map.put("list",list);
        return map;
    }

    @RequestMapping(value = "/addcomment",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addcomment(@ModelAttribute Comment comment){
        try {
           commentService.addComment(comment);
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败"+e;
        }
        return "添加成功";
    }

    @RequestMapping(value = "/addarticle",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String addArticle(@ModelAttribute Article article,HttpServletRequest request){
        try {
            String realPath = request.getSession().getServletContext().getRealPath(HttpInfo.IMG_URL)+"/";
            CreateTxtAndWrite.contentToTxt(realPath+article.getArticle_name()+ ".txt",article.getArticle_context());
            article.setArticle_path("/"+article.getArticle_name()+ ".txt");
            articleService.addArticle(article);
        }catch (Exception e)
        {
            e.printStackTrace();
            return "添加失败"+e;
        }
        return "添加成功";
    }


    @RequestMapping(value = "/deletearticle/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String deleteArticle(@PathVariable("id")Integer id,HttpServletRequest request) {
        Article article = new Article();
        if(id == null || id.equals("")) {
            return "删除失败";
        }
            article.setArticle_id(id);
            try {
                filefunction.filedelete(request.getSession().getServletContext().getRealPath(HttpInfo.IMG_URL)+article.getArticle_path());
                articleService.deleteArticle(article);
            } catch (Exception e) {
                e.printStackTrace();
                logger.info("删除失败" + e.toString());
                return "删除失败" + e;
            }
            logger.info("删除成功");
        return "删除成功";
    }

    @RequestMapping(value = "/showarticle/{id}",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
    public String showOne(@PathVariable("id")Integer id,Model model,HttpServletRequest request) {

        Article article = articleService.findArticle(id);

        String context = CreateTxtAndWrite.readTxtFile(request.getSession().getServletContext().getRealPath(HttpInfo.IMG_URL)+article.getArticle_path());
        article.setArticle_context(context);

        model.addAttribute("article",article);

        Comment comment = new Comment();
        comment.setArticleId(article.getArticle_id());

        model.addAttribute("commentList",commentService.findCommentAll(comment));

        return "front/showArticle";
    }

    @RequestMapping(value = "/article",produces = "text/html;charset=UTF-8")
    public String showArticle(Model model,@RequestParam(value = "title",required = false)String title,Page page,
                          @RequestParam(value = "currentPage",defaultValue = "1",required=false)int currentPage,
                          @RequestParam(value = "flag",required=false,defaultValue = "0")Integer flag,
                          @RequestParam(value = "num",required=false)Integer num,
                          HttpServletRequest request) {
        if (title != null) {
            try {
                if (title != null && title.equals(new String(title.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                    title = new String(title.getBytes("ISO-8859-1"), "utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (num != null) {page.setPageNumber(num);} else {num=page.getPageNumber();}
        totalNumber = articleService.countArticle(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        page.setDbNumber(20);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info(articleService.findArticleAll(parameter).toString());
        model.addAttribute("articleList",articleService.findArticleAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","article");

        String realPath = request.getSession().getServletContext().getRealPath("");
        model.addAttribute("realPath",realPath);

        return "front/article";
    }


    @RequestMapping(value = "/articleList",produces = "text/html;charset=UTF-8")
    public String showArticleList(Model model,@RequestParam(value = "title",required = false)String title,Page page,
                              @RequestParam(value = "currentPage",defaultValue = "1",required=false)int currentPage,
                              @RequestParam(value = "flag",required=false,defaultValue = "0")Integer flag,
                              @RequestParam(value = "num",required=false)Integer num,
                              HttpServletRequest request) {
        if (title != null) {
            try {
                if (title != null && title.equals(new String(title.getBytes("ISO-8859-1"), "ISO-8859-1"))) {
                    title = new String(title.getBytes("ISO-8859-1"), "utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if (num != null) {page.setPageNumber(num);} else {num=page.getPageNumber();}
        totalNumber = articleService.countArticle(title);
        page.setCurrentPage(currentPage);
        page.setTotalNumber(totalNumber);
        logger.info(page.toString());
        Map<String,Object> parameter = new HashMap<String, Object>();
        parameter.put("title",title);
        parameter.put("page",page);
        parameter.put("flag",flag);
        logger.info(articleService.findArticleAll(parameter).toString());
        model.addAttribute("articleList",articleService.findArticleAll(parameter));
        model.addAttribute("num", num);
        model.addAttribute("page",page);
        model.addAttribute("flag",flag);
        model.addAttribute("title",title);
        model.addAttribute("keywords","articleList");

        String realPath = request.getSession().getServletContext().getRealPath("");
        model.addAttribute("realPath",realPath);

        return "manager/articleList";
    }



}
