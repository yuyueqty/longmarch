package top.longmarch.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.beanutils.ConvertUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.longmarch.core.common.Constant;
import top.longmarch.core.common.PageFactory;
import top.longmarch.core.exception.LongmarchException;
import top.longmarch.core.shiro.realm.CustomRealm;
import top.longmarch.core.utils.PasswordUtil;
import top.longmarch.core.enums.StatusEnum;
import top.longmarch.sys.dao.UserDao;
import top.longmarch.sys.entity.SysParams;
import top.longmarch.sys.entity.User;
import top.longmarch.sys.entity.UserRoleRel;
import top.longmarch.sys.service.IParameterService;
import top.longmarch.sys.service.IUserRoleRelService;
import top.longmarch.sys.service.IUserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户信息 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-01-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private static String CREATE_MESSAGE = "创建用户[{}]角色{}完成";
    private static String DELETE_MESSAGE = "删除用户[{}]角色{}完成";
    private static String DELETE_ALL_MESSAGE = "删除用户[{}]所有角色完成";
    @Autowired
    private UserDao userDao;
    @Autowired
    private IUserRoleRelService userRoleRelService;
    @Autowired
    private CustomRealm customRealm;
    @Autowired
    private IParameterService parameterService;

    @Override
    public IPage<User> search(Map<String, Object> params) {
        params = PageFactory.buildMap(params);
        Page<User> page = PageFactory.getInstance(params);
        return userDao.search(page, params);
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        User userDB = this.getOne(new LambdaQueryWrapper<User>().eq(User::getStatus, StatusEnum.YES).eq(User::getUsername, user.getUsername()));
        if (userDB != null) {
            throw new LongmarchException("账号已存在");
        }
        if (StrUtil.isBlank(user.getPassword())) {
            user.setPassword(PasswordUtil.defaultPassword());
        } else {
            user.setPassword(PasswordUtil.password(user.getPassword()));
        }
        SysParams sysParams = JSONUtil.toBean(parameterService.getParameterByName(Constant.SYS_PARAMS).getParamValue(), SysParams.class);
        user.setHeadImgUrl(sysParams.getHeadImgUrl());
        if (StrUtil.isBlank(user.getNickname())) {
            user.setNickname(sysParams.getDefaultNickname());
        }
        this.save(user);
        this.createUserRolesRel(user.getId(), this.str2List(user.getRoleIds()));
    }

    @Transactional
    @Override
    public void updateUser(User user) {
        this.updateById(user);
        this.createUserRolesRel(user.getId(), this.str2List(user.getRoleIds()));
        // 清除用户权限信息
        customRealm.clearCache(user.getUsername());
    }

    @Transactional
    @Override
    public void removeUser(List<Long> userIds) {
        this.removeByIds(userIds);
        userRoleRelService.remove(new LambdaQueryWrapper<UserRoleRel>().in(UserRoleRel::getUserId, userIds));
    }

    @Override
    public void updateUserLoginInfo(Long userId) {
        userDao.updateUserLoginInfo(userId);
    }

    public void createUserRolesRel(Long userId, List<Long> roleIdList) {
        if (null == roleIdList) {
            return;
        }
        List<UserRoleRel> dbUserRoleRelList = userRoleRelService.list(new LambdaQueryWrapper<UserRoleRel>().eq(UserRoleRel::getUserId, userId));
        List<UserRoleRel> insertUserRoleRelList = null;
        if (roleIdList.size() == 0) {
            userRoleRelService.remove(new LambdaQueryWrapper<UserRoleRel>().eq(UserRoleRel::getUserId, userId));
            log.info(DELETE_ALL_MESSAGE, userId);
        } else if (dbUserRoleRelList == null || dbUserRoleRelList.size() == 0) {
            insertUserRoleRelList = buildInsertUserRoleRelList(userId, roleIdList);
            userRoleRelService.saveBatch(insertUserRoleRelList);
            log.info(CREATE_MESSAGE, userId, roleIdList);
        } else {
            List<Long> dbRoleIdList = dbUserRoleRelList.stream().map(UserRoleRel::getRoleId).collect(Collectors.toList());
            List<Long> deleteRoleIdList = dbRoleIdList.stream().filter(id -> !roleIdList.contains(id)).collect(Collectors.toList());
            List<Long> insertRoleIdList = roleIdList.stream().filter(id -> !dbRoleIdList.contains(id)).collect(Collectors.toList());
            if (deleteRoleIdList != null && deleteRoleIdList.size() > 0) {
                userRoleRelService.remove(new QueryWrapper<UserRoleRel>().lambda().eq(UserRoleRel::getUserId, userId).in(UserRoleRel::getRoleId, deleteRoleIdList));
                log.info(DELETE_MESSAGE, userId, deleteRoleIdList);
            }
            if (insertRoleIdList != null && insertRoleIdList.size() > 0) {
                insertUserRoleRelList = buildInsertUserRoleRelList(userId, insertRoleIdList);
                userRoleRelService.saveBatch(insertUserRoleRelList);
                log.info(CREATE_MESSAGE, userId, insertRoleIdList);
            }
        }
    }

    private List<UserRoleRel> buildInsertUserRoleRelList(Long userId, List<Long> roleIdList) {
        return roleIdList.stream().map(roleId -> {
            UserRoleRel userRoleRel = new UserRoleRel();
            userRoleRel.setUserId(userId);
            userRoleRel.setRoleId(roleId);
            return userRoleRel;
        }).collect(Collectors.toList());
    }

    private List<Long> str2List(String str) {
        List<Long> roleIdList = new ArrayList<>();
        if (StrUtil.isNotBlank(str)) {
            String[] split = str.split(",");
            roleIdList = Arrays.asList((Long[]) ConvertUtils.convert(split, Long.class));
        }
        return roleIdList;
    }

}
