package top.longmarch.wx.entity.export;

import lombok.Data;

@Data
public class SearchDTO {

    private Long gzhId;
    private Long createBy;
    private Integer fieldId;

    public SearchDTO() {
    }

    public SearchDTO(Long gzhId, Long createBy, Integer fieldId) {
        this.gzhId = gzhId;
        this.createBy = createBy;
        this.fieldId = fieldId;
    }
}
