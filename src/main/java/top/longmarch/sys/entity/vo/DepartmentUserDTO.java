package top.longmarch.sys.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class DepartmentUserDTO {

    private Long depId;

    private Long userId;

    private String username;

    private String depName;

    private boolean checked;

    private List<Long> checkedKeys;

}
