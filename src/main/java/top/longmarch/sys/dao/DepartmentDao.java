package top.longmarch.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.longmarch.sys.entity.Department;
import top.longmarch.sys.entity.vo.DepartmentTree;
import top.longmarch.sys.entity.vo.DepartmentUserDTO;

import java.util.List;

/**
 * <p>
 * 部门信息 Mapper 接口
 * </p>
 *
 * @author YuYue
 * @since 2020-02-05
 */
public interface DepartmentDao extends BaseMapper<Department> {

    List<DepartmentTree> getDepartmentTree();

    List<DepartmentUserDTO> handleLoadDepartmentUsers(Long depId);
}
