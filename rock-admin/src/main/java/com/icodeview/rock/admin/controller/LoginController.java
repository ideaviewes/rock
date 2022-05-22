package com.icodeview.rock.admin.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.icodeview.rock.admin.pojo.RbacUser;
import com.icodeview.rock.admin.vo.LoginVo;
import com.icodeview.rock.dto.admin.LoginDto;
import com.icodeview.rock.exception.BadHttpRequestException;
import com.icodeview.rock.admin.security.JwtAuthService;
import com.icodeview.rock.security.AuthenticationIgnore;
import com.icodeview.rock.security.AuthorizationIgnore;
import com.icodeview.rock.vo.CommonResult;
import com.nimbusds.jose.JOSEException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.annotation.security.PermitAll;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户登录")
@RestController
@RequestMapping("/login")
public class LoginController {
    @Resource
    private JwtAuthService jwtAuthService;
    @PermitAll
    @ApiOperation(value = "用户名密码登录")
    @PostMapping("account")
    public CommonResult<LoginVo> account(@RequestBody @Validated LoginDto dto){
        try {
            LoginVo result = jwtAuthService.login(dto.getUsername(), dto.getPassword());
            return CommonResult.success("登录成功！",result);
        } catch (JsonProcessingException | JOSEException e) {
            e.printStackTrace();
            throw new BadHttpRequestException("登录失败，请重试！");
        }
    }
}
