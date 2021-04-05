package com.icodeview.rock.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RoleDto {
    private Integer id;
    @NotBlank(message = "请填写角色名称！")
    private String name;
    @NotBlank(message = "请填写角色编码")
    private String code;
    @NotBlank(message = "请填写角色描述")
    private String remark;
}
