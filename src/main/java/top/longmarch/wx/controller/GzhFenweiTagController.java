package top.longmarch.wx.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.LmUtils;
import top.longmarch.core.utils.UserUtil;
import top.longmarch.sys.entity.Dictionary;
import top.longmarch.sys.entity.vo.ChangeStatusDTO;
import top.longmarch.sys.service.IDictionaryService;
import top.longmarch.wx.entity.*;
import top.longmarch.wx.service.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 公众号粉丝分维解析标签 前端控制器
 * </p>
 *
 * @author YuYue
 * @since 2020-04-19
 */
@Api(value = "公众号粉丝分维解析标签模块", tags = "公众号粉丝分维解析标签模块接口")
@RestController
@RequestMapping("/wx/gzh-fenwei-tag")
public class GzhFenweiTagController {

    private static final Logger log = LoggerFactory.getLogger(GzhFenweiTagController.class);
    @Autowired
    private IGzhFenweiTagService gzhFenweiTagService;
    @Autowired
    private IGzhAccountService gzhAccountService;
    @Autowired
    private IGzhUserService gzhUserService;
    @Autowired
    private IDictionaryService dictionaryService;
    @Autowired
    private IFwtagRuleService fwtagRuleService;
    @Autowired
    private IGzhTagService gzhTagService;
    @Autowired
    private IGzhTagRuleService gzhTagRuleService;
    private static final String blank = " ";


    @ApiOperation(value = "搜索公众号粉丝分维解析标签")
    @PostMapping("/search")
    public Result search(@RequestBody(required = false) Map<String, Object> params) {
        IPage<GzhFenweiTag> page = PageFactory.getInstance(params);
        LambdaQueryWrapper<GzhFenweiTag> wrapper = new LambdaQueryWrapper<>();

        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getDefaultAccount, 1)
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId()));
        if (gzhAccount == null) {
            wrapper.eq(GzhFenweiTag::getGzhId, -1).eq(GzhFenweiTag::getFieldId, -1);
        } else {
            wrapper.eq(GzhFenweiTag::getGzhId, gzhAccount.getId()).eq(GzhFenweiTag::getFieldId, gzhAccount.getFwField());
        }
        wrapper.eq(LmUtils.isNotBlank(params.get("openId")), GzhFenweiTag::getOpenId, params.get("openId"));
        return Result.ok().add(gzhFenweiTagService.page(page, wrapper));
    }

    @ApiOperation(value = "获取用户标签")
    @GetMapping("/openid/{openId}")
    public Result getUserTagByOpenId(@PathVariable("openId") String openId) {
        GzhAccount gzhAccount = gzhAccountService.getOne(new LambdaQueryWrapper<GzhAccount>()
                .eq(GzhAccount::getDefaultAccount, 1)
                .eq(GzhAccount::getCreateBy, UserUtil.getUserId()));
        if (gzhAccount == null) {
            return Result.fail("未设置默认公众号");
        }

        List<GzhFenweiTag> gzhFenweiTagList = gzhFenweiTagService.list(new QueryWrapper<GzhFenweiTag>()
                .select("distinct `name`, score, content")
                .eq("create_by", UserUtil.getUserId())
                .eq("gzh_id", gzhAccount.getId())
                .eq("open_id", openId)
                .groupBy("name"));

        LambdaQueryWrapper<GzhUser> userWrapper = new LambdaQueryWrapper<GzhUser>()
                .eq(GzhUser::getCreateBy, UserUtil.getUserId())
                .eq(GzhUser::getGzhId, gzhAccount.getId())
                .eq(GzhUser::getOpenId, openId);
        GzhUser gzhUser = gzhUserService.getOne(userWrapper);

        Dictionary dict = dictionaryService.getOne(new LambdaQueryWrapper<Dictionary>()
                .eq(Dictionary::getCode, "fw_field_dict")
                .eq(Dictionary::getValue, gzhAccount.getFwField()));

        List<String> list1 = new ArrayList<>();
        List<Map<String, Object>> list2 = new ArrayList<>();
        List<String> list3 = new ArrayList<>();
        gzhFenweiTagList.forEach(t -> {
            String content = t.getContent();
            Integer score = t.getScore();
            list1.add(content);
            Map<String, Object> m = new HashMap<>();
            m.put("name", content);
            m.put("value", score);
            list2.add(m);
        });

        String newTag = getTag(gzhAccount.getId(), gzhFenweiTagList);
        StringBuffer sb = new StringBuffer();
        sb.append("行业：").append(dict.getLabel()).append(blank).append("用户：").append(gzhUser.getNickname()).append(blank);
        if (StrUtil.isNotBlank(newTag)) {
            sb.append("新标签：").append(newTag);
        }
        Map<String, Object> map = new HashMap<>();
        map.put("info", sb.toString());
        map.put("names", list1);
        map.put("tags", list2);
        map.put("newTags", list3);
        return Result.ok().add(map);
    }

    private String getTag(Long gzhId, List<GzhFenweiTag> tags) {
        List<String> tagNewList = new ArrayList<>();
        List<GzhTagRule> gzhTagRuleList = gzhTagRuleService.list(new LambdaQueryWrapper<GzhTagRule>()
                .eq(GzhTagRule::getCreateBy, UserUtil.getUserId())
                .eq(GzhTagRule::getGzhId, gzhId));
        Map<Long, List<GzhTagRule>> collect = gzhTagRuleList.stream().collect(Collectors.groupingBy(GzhTagRule::getTagId, Collectors.toList()));
        for (Map.Entry<Long, List<GzhTagRule>> entry : collect.entrySet()) {
            List<GzhTagRule> list = entry.getValue();
            Map<String, Integer> ruleMap = list.stream().collect(Collectors.toMap(GzhTagRule::getRid, GzhTagRule::getScore));
            Map<String, Integer> userMap = tags.stream().collect(Collectors.toMap(GzhFenweiTag::getName, GzhFenweiTag::getScore));
            boolean present = ruleMap.keySet().stream().findFirst().filter(k -> ruleMap.get(k) > (userMap.get(k) == null ? 0 : userMap.get(k))).isPresent();
            if (!present) {
                GzhTag gzhTag = gzhTagService.getById(entry.getKey());
                tagNewList.add(gzhTag.getName());
            }
        }
        return String.join(",", tagNewList);
    }

    @ApiOperation(value = "查看公众号粉丝分维解析标签")
    @RequiresPermissions("wx:gzhFenweiTag:show")
    @GetMapping("/show/{id}")
    public Result show(@PathVariable("id") Long id) {
        GzhFenweiTag gzhFenweiTag = gzhFenweiTagService.getById(id);
        return Result.ok().add(gzhFenweiTag);
    }

    @Log
    @ApiOperation(value = "创建公众号粉丝分维解析标签")
    @RequiresPermissions("wx:gzhFenweiTag:create")
    @PostMapping("/create")
    public Result create(@Validated @RequestBody GzhFenweiTag gzhFenweiTag) {
        log.info("创建公众号粉丝分维解析标签, 入参：{}", gzhFenweiTag);
        gzhFenweiTagService.save(gzhFenweiTag);
        return Result.ok().add(gzhFenweiTag);
    }

    @Log
    @ApiOperation(value = "更新公众号粉丝分维解析标签")
    @RequiresPermissions("wx:gzhFenweiTag:update")
    @PostMapping("/update")
    public Result update(@Validated @RequestBody GzhFenweiTag gzhFenweiTag) {
        log.info("更新公众号粉丝分维解析标签, 入参：{}", gzhFenweiTag);
        gzhFenweiTagService.updateById(gzhFenweiTag);
        return Result.ok().add(gzhFenweiTag);
    }

    @Log
    @ApiOperation(value = "删除公众号粉丝分维解析标签")
    @RequiresPermissions("wx:gzhFenweiTag:delete")
    @PostMapping("/delete")
    public Result delete(@RequestBody Long[] ids) {
        log.info("删除公众号粉丝分维解析标签, ids={}", ids);
        gzhFenweiTagService.removeByIds(Arrays.asList(ids));
        return Result.ok();
    }

    @Log
    @ApiOperation(value = "修改公众号粉丝分维解析标签状态")
    @RequiresPermissions("wx:gzhFenweiTag:update")
    @PostMapping("/changeStatus")
    public Result changeStatus(@RequestBody ChangeStatusDTO changeStatusDTO) {
        log.info("修改公众号粉丝分维解析标签状态, 入参：{}", changeStatusDTO);
        GzhFenweiTag gzhFenweiTag = new GzhFenweiTag();
        BeanUtils.copyProperties(changeStatusDTO, gzhFenweiTag);
        gzhFenweiTagService.updateById(gzhFenweiTag);
        return Result.ok().add(gzhFenweiTag);
    }

}
