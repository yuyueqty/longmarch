package top.longmarch.sys.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleAddUserDTO {

    private Long id;
    private List<Long> checkedKeys;

}
