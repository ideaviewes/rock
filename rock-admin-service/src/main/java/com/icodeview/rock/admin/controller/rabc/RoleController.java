package com.icodeview.rock.admin.controller.rabc;

import com.icodeview.rock.admin.dto.RoleDto;
import com.icodeview.rock.admin.pojo.RbacRole;
import com.icodeview.rock.admin.service.RbacRoleService;
import com.icodeview.rock.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Api(tags = "权限管理--角色管理")
@RestController
@RequestMapping("/rbac/role")
public class RoleController {
    @Resource
    private RbacRoleService rbacRoleService;
    @ApiOperation("角色列表")
    @GetMapping("index")
    public CommonResult<List<RbacRole>> index(){
        List<RbacRole> list = rbacRoleService.getIndex();
        return CommonResult.success(list);
    }
    @ApiOperation("添加角色")
    @PostMapping("create")
    public CommonResult create(@RequestBody @Validated RoleDto dto){
        rbacRoleService.createRole(dto);
        return CommonResult.success("添加成功！");
    }
    @ApiOperation("更新角色")
    @PostMapping("update")
    public CommonResult update(@RequestBody @Validated RoleDto dto){
        rbacRoleService.updateRole(dto);
        return CommonResult.success("更新成功！");
    }
    @ApiOperation("删除角色")
    @PostMapping("delete")
    @ApiImplicitParam(value = "角色id",name = "id",required = true)
    public CommonResult delete(@RequestParam(value = "id") Long id){
        rbacRoleService.deleteRole(id);
        return CommonResult.success("删除成功！");
    }
}
