package com.icodeview.rock.admin.controller.rabc;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.icodeview.rock.admin.dto.RbacPermissionDto;
import com.icodeview.rock.admin.pojo.RbacPermission;
import com.icodeview.rock.admin.service.RbacPermissionService;
import com.icodeview.rock.security.AuthorizationIgnore;
import com.icodeview.rock.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "权限管理-权限管理")
@ApiSort(3)
@RestController("RbacPermission")
@RequestMapping("/rbac/permission")
public class PermissionController {
    @Resource
    private RbacPermissionService rbacPermissionService;

    @AuthorizationIgnore
    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "权限列表")
    @GetMapping("index")
    public CommonResult<List<RbacPermission>> index(){
        List<RbacPermission> list = rbacPermissionService.getIndex();
        return CommonResult.success(list);
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "添加权限")
    @PostMapping("create")
    public CommonResult<Void> create(@RequestBody @Validated RbacPermissionDto dto){
        rbacPermissionService.createPermission(dto);
        return CommonResult.success("添加成功");
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "编辑权限")
    @PostMapping("update")
    public CommonResult<Void> update(@RequestBody @Validated RbacPermissionDto dto){
        rbacPermissionService.updatePermission(dto);
        return CommonResult.success("编辑成功");
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "删除权限")
    @ApiImplicitParam(value = "权限id",name = "id",required = true)
    @PostMapping("delete")
    public CommonResult<Void> delete(@RequestParam(value = "id") Long id){
        rbacPermissionService.delete(id);
        return CommonResult.success("删除成功！");
    }
}
