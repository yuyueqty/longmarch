package top.longmarch.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.longmarch.core.common.Result;
import top.longmarch.core.utils.tree.TreeUtil;
import top.longmarch.sys.dao.DepartmentDao;
import top.longmarch.sys.entity.Department;
import top.longmarch.sys.entity.vo.DepartmentTree;
import top.longmarch.sys.entity.vo.DepartmentUserDTO;
import top.longmarch.sys.service.IDepartmentService;

import java.util.*;

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


    @Override
    public List<DepartmentTree> getDepartmentTree() {
        return TreeUtil.list2Tree(departmentDao.getDepartmentTree());
    }

    @Override
    public List<DepartmentUserDTO> handleLoadDepartmentUsers(Long depId) {
        return departmentDao.handleLoadDepartmentUsers(depId);
    }

    @Override
    public Set<Long> getDownDeptIds(Long deptId) {
        Set<Long> deptIds = new LinkedHashSet<>();
        deptIds.add(deptId);
        List<Department> departmentList = this.list(new LambdaQueryWrapper<Department>().eq(Department::getParentId, deptId));
        if (departmentList != null && departmentList.size() > 0) {
            forLoop(departmentList, deptIds);
        }
        return deptIds;
    }

    private void forLoop(List<Department> departmentList, Set<Long> deptIds) {
        for (Department department : departmentList) {
            deptIds.add(department.getId());
            List<Department> list = this.list(new LambdaQueryWrapper<Department>().eq(Department::getParentId, department.getId()));
            if (list != null && list.size() > 0) {
                forLoop(list, deptIds);
            }
        }
    }

    @Override
    public List<Long> getUpDeptIds(Long deptId) {
        List<Long> deptIds = new ArrayList<>();
        Department department = this.getById(deptId);
        if (department == null) {
            Result.ok().add(deptIds);
        }
        if (department.getParentId() == 0) {
            deptIds.add(deptId);
            Result.ok().add(deptIds);
        }
        forLoop(deptIds, department);
        Collections.sort(deptIds);
        return deptIds;
    }

    private void forLoop(List<Long> deptIds, Department department) {
        if (department.getParentId() == 0) {
            return;
        }
        deptIds.add(department.getParentId());
        forLoop(deptIds, this.getById(department.getParentId()));
    }

}
