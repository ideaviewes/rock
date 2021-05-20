package com.icodeview.rock.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class StatusDto {
    @NotNull(message = "请填写id！")
    private Integer id;
    private String remark;
    @NotNull(message = "请填写状态！")
    private Integer status;
}
