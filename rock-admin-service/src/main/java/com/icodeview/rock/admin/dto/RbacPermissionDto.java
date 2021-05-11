package com.icodeview.rock.admin.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class RbacPermissionDto {
    private Integer id;
    private Integer parentId;
    @NotBlank(message = "请填写权限名称")
    private String name;
    private String icon;
    @NotBlank(message = "请填写路由")
    private String url;
    private Integer hideInMenu;
    private Long priority;
    private String access;
}
