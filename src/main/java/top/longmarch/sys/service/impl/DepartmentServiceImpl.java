package top.longmarch.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.utils.tree.TreeUtil;
import top.longmarch.sys.dao.DepartmentDao;
import top.longmarch.sys.entity.Department;
import top.longmarch.sys.entity.DepartmentUserRel;
import top.longmarch.sys.entity.vo.DepartmentTree;
import top.longmarch.sys.entity.vo.DepartmentUserDTO;
import top.longmarch.sys.service.IDepartmentService;
import top.longmarch.sys.service.IDepartmentUserRelService;
import top.longmarch.sys.service.IUserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 部门信息 服务实现类
 * </p>
 *
 * @author YuYue
 * @since 2020-02-05
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentDao, Department> implements IDepartmentService {

    @Autowired
    private DepartmentDao departmentDao;
    @Autowired
    private IDepartmentUserRelService departmentUserRelService;
    @Autowired
    private IUserService userService;


    @Override
    public List<DepartmentTree> getDepartmentList() {
        return TreeUtil.list2Tree(departmentDao.getDepartmentList());
    }

    @Override
    public List<DepartmentUserDTO> handleLoadDepartmentUsers(Long depId) {
        return departmentDao.handleLoadDepartmentUsers(depId);
    }

    @Override
    public void addDepartmentUsers(DepartmentUserDTO departmentUserDTO) {
        // 页面选择的用户ID集合
        List<Long> selectUserIds = departmentUserDTO.getCheckedKeys();
        if (selectUserIds == null || selectUserIds.size() == 0) {
            return;
        }
        List<DepartmentUserRel> departmentUserRelList = departmentUserRelService.list(new LambdaQueryWrapper<DepartmentUserRel>()
                .eq(DepartmentUserRel::getDepartmentId, departmentUserDTO.getDepId()));
        List<Long> insertUserIds = null;
        List<Long> deleteUserIds = null;
        if (departmentUserRelList == null || departmentUserRelList.size() == 0) {
            // 添加所有用户到该部门下面
            insertUserIds = selectUserIds;
        } else {
            List<Long> dhUserIds = departmentUserRelList.stream().map(DepartmentUserRel::getUserId).collect(Collectors.toList());
            insertUserIds = selectUserIds.stream().filter(id -> !dhUserIds.contains(id)).collect(Collectors.toList());
            deleteUserIds = dhUserIds.stream().filter(id -> !selectUserIds.contains(id)).collect(Collectors.toList());
        }
        if (insertUserIds != null && insertUserIds.size() > 0) {
            List<DepartmentUserRel> insertDepartmentUserRelList = new ArrayList<>();
            for (Long userId : insertUserIds) {
                DepartmentUserRel departmentUserRel = new DepartmentUserRel();
                departmentUserRel.setDepartmentId(departmentUserDTO.getDepId());
                departmentUserRel.setUserId(userId);
                insertDepartmentUserRelList.add(departmentUserRel);
            }
            departmentUserRelService.saveBatch(insertDepartmentUserRelList);
        }
        if (deleteUserIds != null && deleteUserIds.size() > 0) {
            departmentUserRelService.remove(new LambdaQueryWrapper<DepartmentUserRel>()
                    .eq(DepartmentUserRel::getDepartmentId, departmentUserDTO.getDepId()).in(DepartmentUserRel::getUserId, deleteUserIds));
        }
    }

}
