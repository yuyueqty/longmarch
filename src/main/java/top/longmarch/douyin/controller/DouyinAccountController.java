package top.longmarch.douyin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.longmarch.core.common.Result;
import top.longmarch.douyin.service.DouyinAccountService;

@RestController
@RequestMapping("/douyin")
public class DouyinAccountController {

    @Autowired
    private DouyinAccountService douyinAccountService;

    @GetMapping("/list")
    public Result list() {
        return Result.ok().add(douyinAccountService.list());
    }

}
