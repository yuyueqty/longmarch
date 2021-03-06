package top.longmarch.test.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.longmarch.core.annotation.Log;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.LmUtils;
import top.longmarch.sys.entity.dto.ChangeStatusDTO;
import top.longmarch.test.entity.Member;
import top.longmarch.test.service.IMemberService;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 会员 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-02-29
 */
@Api(value = "会员模块", tags = "会员模块接口")
@RestController
@RequestMapping("/test/member")
public class MemberController {

    private static final Logger log = LoggerFactory.getLogger(MemberController.class);
    @Autowired
    private IMemberService memberService;

    @ApiOperation(value = "搜索会员")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<Member> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(LmUtils.isNotBlank(params.get("sex")), Member::getSex, params.get("sex"));
        wrapper.eq(LmUtils.isNotBlank(params.get("status")), Member::getStatus, params.get("status"));

        Object fuzzySearch = params.get(Constant.FUZZY_SEARCH);
        wrapper.and(LmUtils.isNotBlank(fuzzySearch), p -> p.like(Member::getName, fuzzySearch)
                .or().like(Member::getNickname, fuzzySearch));

        if (LmUtils.isNotBlank(params.get("createTime"))) {
            List<Object> createTime = (List<Object>) params.get("createTime");
            wrapper.between(Member::getCreateTime, createTime.get(0), createTime.get(1));
        }
        if (LmUtils.isNotBlank(params.get("loginTime"))) {
            List<Object> loginTime = (List<Object>) params.get("loginTime");
            wrapper.between(Member::getLoginTime, loginTime.get(0), loginTime.get(1));
        }

        Object id = params.get(Constant.PROP);
        Object idOrder = params.get(Constant.ORDER);
        if (LmUtils.isNotBlank(id) && LmUtils.isNotBlank(idOrder)) {
            boolean isAsc = "ascending".equals(idOrder);
            wrapper.orderBy(true, isAsc, Member::getId);
        }
        Object sex = params.get(Constant.PROP);
        Object sexOrder = params.get(Constant.ORDER);
        if (LmUtils.isNotBlank(sex) && LmUtils.isNotBlank(sexOrder)) {
            boolean isAsc = "ascending".equals(sexOrder);
            wrapper.orderBy(true, isAsc, Member::getSex);
        }
        Object createTime = params.get(Constant.PROP);
        Object createTimeOrder = params.get(Constant.ORDER);
        if (LmUtils.isNotBlank(createTime) && LmUtils.isNotBlank(createTimeOrder)) {
            boolean isAsc = "ascending".equals(createTimeOrder);
            wrapper.orderBy(true, isAsc, Member::getCreateTime);
        }
        return Result.ok().add(memberService.page(page, wrapper));
    }

    @ApiOperation(value = "查看会员")
    @RequiresPermissions("test:member:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        Member member = memberService.getById(id);
        return Result.ok().add(member);
    }

    @Log
    @ApiOperation(value = "创建会员")
    @RequiresPermissions("test:member:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody Member member) {
        log.info("创建会员, 入参：{}", member);
        memberService.save(member);
        return Result.ok().add(member);
    }

    @Log
    @ApiOperation(value = "更新会员")
    @RequiresPermissions("test:member:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody Member member) {
        log.info("更新会员, 入参：{}", member);
        memberService.updateById(member);
        return Result.ok().add(member);
    }

    @Log
    @ApiOperation(value = "删除会员")
    @RequiresPermissions("test:member:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除会员, ids={}", ids);
        memberService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改会员状态")
    @RequiresPermissions("test:member:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改会员状态, 入参：{}", changeStatusDTO);
        Member member = new Member();
        BeanUtils.copyProperties(changeStatusDTO, member);
        memberService.updateById(member);
        return Result.ok().add(member);
    }

}
