package com.icodeview.rock.admin.controller.rabc;

import com.icodeview.rock.admin.pojo.RbacUser;
import com.icodeview.rock.vo.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "权限管理--用户管理")
@RestController("RbacUserController")
@RequestMapping("/rbac/user")
public class UserController {

    @ApiOperation("当前用户信息")
    @GetMapping("current")
    public CommonResult<RbacUser> current(@ApiIgnore @AuthenticationPrincipal RbacUser rbacUser){
        return CommonResult.success(rbacUser);
    }
}
