package com.icodeview.rock.admin.controller.rabc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.icodeview.rock.admin.pojo.RbacUser;
import com.icodeview.rock.admin.service.RbacUserService;
import com.icodeview.rock.admin.vo.MenuDataItem;
import com.icodeview.rock.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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

    @ApiOperationSupport(order = 1,author = "781613629@qq.com")
    @ApiOperation("当前用户信息")
    @GetMapping("current")
    public CommonResult<RbacUser> current(@ApiIgnore @AuthenticationPrincipal RbacUser rbacUser){
        return CommonResult.success(rbacUser);
    }

    @ApiOperationSupport(order = 2,author = "781613629@qq.com")
    @ApiOperation("当前用户菜单")
    @GetMapping("menu")
    private void menu(@ApiIgnore @AuthenticationPrincipal RbacUser user, HttpServletResponse response) throws IOException {
        Integer id = user.getId();
        List<MenuDataItem> list = rbacUserService.getMenuByUserId(id);
        CommonResult<List<MenuDataItem>> result=CommonResult.success("获取数据成功！",list);
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(result);
        response.addHeader("Content-Type","application/json;charset=utf-8");
        response.getWriter().write(content);
    }
}
