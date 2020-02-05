package top.longmarch.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.longmarch.sys.entity.Department;
import top.longmarch.sys.entity.vo.DepartmentTree;
import top.longmarch.sys.entity.vo.DepartmentUserDTO;

import java.util.List;

/**
 * <p>
 * 部门信息 服务类
 * </p>
 *
 * @author YuYue
 * @since 2020-02-05
 */
public interface IDepartmentService extends IService<Department> {

    List<DepartmentTree> getDepartmentList();

    List<DepartmentUserDTO> handleLoadDepartmentUsers(Long depId);

    void addDepartmentUsers(DepartmentUserDTO departmentUserDTO);
}
