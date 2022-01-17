package com.icodeview.rock.admin.controller.rabc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.icodeview.rock.admin.dto.RbacUserDto;
import com.icodeview.rock.admin.dto.StatusDto;
import com.icodeview.rock.admin.pojo.RbacUser;
import com.icodeview.rock.admin.service.RbacUserService;
import com.icodeview.rock.admin.vo.MenuDataItem;
import com.icodeview.rock.admin.vo.RbacUserVo;
import com.icodeview.rock.security.AuthorizationIgnore;
import com.icodeview.rock.vo.CommonResult;
import com.icodeview.rock.vo.PageResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Api(tags = "权限管理--用户管理")
@ApiSort(1)
@RestController("RbacUserController")
@RequestMapping("/rbac/user")
public class UserController {
    @Resource
    private RbacUserService rbacUserService;

    @AuthorizationIgnore
    @ApiOperationSupport(order = 1, author = "781613629@qq.com")
    @ApiOperation("当前用户信息")
    @GetMapping("current")
    public CommonResult<RbacUser> current(@ApiIgnore @AuthenticationPrincipal RbacUser rbacUser) {
        return CommonResult.success(rbacUser);
    }

    @AuthorizationIgnore
    @ApiOperationSupport(order = 2, author = "781613629@qq.com")
    @ApiOperation("当前用户菜单")
    @GetMapping("menu")
    private void menu(@ApiIgnore @AuthenticationPrincipal RbacUser user, HttpServletResponse response) throws IOException {
        Integer id = user.getId();
        List<MenuDataItem> list = rbacUserService.getMenuByUserId(id);
        CommonResult<List<MenuDataItem>> result = CommonResult.success("获取数据成功！", list);
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(result);
        response.addHeader("Content-Type", "application/json;charset=utf-8");
        response.getWriter().write(content);
    }

    @ApiOperationSupport(order = 3, author = "781613629@qq.com")
    @ApiOperation("添加用户")
    @PostMapping("create")
    public CommonResult<Void> create(@RequestBody @Validated RbacUserDto dto) {
        rbacUserService.createUser(dto);
        return CommonResult.success("添加成功！");
    }

    @ApiOperationSupport(order = 4, author = "781613629@qq.com")
    @ApiOperation("编辑用户")
    @PostMapping("update")
    public CommonResult<Void> update(@RequestBody @Validated RbacUserDto dto) {
        rbacUserService.updateUser(dto);
        return CommonResult.success("编辑成功！");
    }

    @ApiOperationSupport(order = 5, author = "781613629@qq.com")
    @ApiOperation("删除用户")
    @GetMapping("delete")
    @ApiImplicitParam(value = "用户id", name = "id", required = true)
    public CommonResult<Void> delete(@RequestParam(value = "id") Integer id) {
        rbacUserService.deleteUser(id);
        return CommonResult.success("删除成功！");
    }

    @ApiOperationSupport(order = 6, author = "781613629@qq.com")
    @ApiOperation("用户状态")
    @PostMapping("status")
    public CommonResult<Void> status(@RequestBody @Validated StatusDto dto) {
        rbacUserService.setUserStatus(dto);
        return CommonResult.success("修改成功！");
    }

    @ApiOperation("用户列表")
    @ApiOperationSupport(order = 7, author = "781613629@qq.com")
    @GetMapping("index")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username",value = "用户名称"),
            @ApiImplicitParam(name = "mobile",value = "手机号"),
            @ApiImplicitParam(name = "status",value = "用户状态"),
            @ApiImplicitParam(name = "current",value = "页码"),
            @ApiImplicitParam(name = "pageSize",value = "条数")
    })

    public CommonResult<PageResult<RbacUserVo>> index(
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "mobile", required = false) String mobile,
            @RequestParam(value = "status", required = false) Integer status,
            @RequestParam(value = "current", required = false, defaultValue = "1") Long pageNum,
            @RequestParam(value = "pageSize", required = false, defaultValue = "20") Long pageSize
    ) {
        PageResult<RbacUserVo> result = rbacUserService.getIndex(username, mobile, status, pageNum, pageSize);
        return CommonResult.success(result);
    }
}
