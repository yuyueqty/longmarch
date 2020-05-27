package top.longmarch.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.longmarch.cms.entity.Article;
import top.longmarch.cms.service.IArticleService;

@Api(value = "开放接口", tags = "微信开放接口")
@Controller
@RequestMapping("/api")
public class CmsOpenApi {

    @Autowired
    private IArticleService articleService;

    @ApiOperation(value = "文章详情")
    @RequestMapping("/article/{id}.html")
    private String getArticleById(@PathVariable Long id, Model model) {
        Article article = articleService.getArticleById(id);
        model.addAttribute("article", article);
        return "/article";
    }

}
